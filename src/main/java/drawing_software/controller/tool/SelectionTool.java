package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.MoveCommand;
import drawing_software.controller.command.ResizeCommand;
import drawing_software.controller.tool.handler.SelectionHandler;
import drawing_software.model.Shape;
import drawing_software.view.Canvas;
import drawing_software.view.menu.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SelectionTool implements Tool {
    private final Canvas canvas;
    private final Invoker invoker;

    private final MouseRequest request = new MouseRequest();

    public SelectionTool(Canvas canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
        request.setStartingPoint(new Point2D.Double(0, 0));
    }

    /**
     * When pressing the mouse, this method checks if the user is pressing a valid shape,
     * then saves the current shape location and the  initial mouse position.
     *
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseLeftPressed(MouseEvent mouseEvent) {
        request.setMouseEvent(mouseEvent);
        new SelectionHandler(canvas).handlePressed(request);

    }

    /**
     * When dragging the mouse, this method checks if the user has pressed a valid shape,
     * then moves the shape and the selection grid according to the movement of the user.
     * The new position of the figure is given translating the location of the figure by a
     * vector (delta_x, delta_y), which components are given by the difference between the
     * coordinates between the current mouse position and the previous mouse position. The latter
     * is updated at the end of the method.
     *
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

        if (canvas.getSelectionGrid() != null) {
            request.setMouseEvent(mouseEvent);
            new SelectionHandler(canvas).handleDragged(request);
        }
    }

    /**
     * When releasing the mouse, this method actually executes the move command, a concrete command
     * representing the update of the location of the figure.
     *
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Shape selectedShape = request.getSelectedShape();
        if (selectedShape == null) return;

        Dimension oldShapeSize = request.getOldShapeSize();
        Point2D oldShapeLocation = request.getOldShapeLocation();
        Rectangle bounds = canvas.getSelectionGrid().getSelectedShape().getBounds();

        if (oldShapeSize != null && !bounds.getSize().equals(oldShapeSize)) {
            invoker.executeCommand(new ResizeCommand(canvas, selectedShape, oldShapeLocation, oldShapeSize));
        } else if (oldShapeLocation != null && !bounds.getLocation().equals(oldShapeLocation)) {
            invoker.executeCommand(new MoveCommand(canvas, selectedShape, oldShapeLocation));
        }

        request.setRatio(canvas.getSelectionGrid().getWidth() / canvas.getSelectionGrid().getHeight());
        canvas.getSelectionGrid().clearSelectedVertex();
    }

    @Override
    public void mouseRightClicked(MouseEvent mouseEvent) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem cutMenuItem = new CutMenuItem(canvas, invoker).createMenuItem();
        cutMenuItem.setFocusable(false);
        popupMenu.add(cutMenuItem);

        JMenuItem copyMenuItem = new CopyMenuItem(canvas, invoker).createMenuItem();
        copyMenuItem.setFocusable(false);
        popupMenu.add(copyMenuItem);

        JMenuItem pasteMenuItem = new PasteMenuItem(canvas, invoker).createMenuItem();
        pasteMenuItem.setFocusable(false);
        popupMenu.add(pasteMenuItem);

        JMenuItem deleteMenuItem = new DeleteMenuItem(canvas, invoker).createMenuItem();
        deleteMenuItem.setFocusable(false);
        popupMenu.add(deleteMenuItem);
        popupMenu.show(canvas, mouseEvent.getX(), mouseEvent.getY());

        JMenuItem bringForward = new BringForwardItem(canvas, invoker).createMenuItem();
        bringForward.setFocusable(false);
        popupMenu.add(bringForward);

        JMenuItem bringBackward = new BringBackwardItem(canvas, invoker).createMenuItem();
        bringBackward.setFocusable(false);
        popupMenu.add(bringBackward);

    }

}