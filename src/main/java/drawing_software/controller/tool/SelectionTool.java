package drawing_software.controller.tool;

import drawing_software.Context;
import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.MoveCommand;
import drawing_software.controller.command.ResizeCommand;
import drawing_software.model.Drawable;
import drawing_software.model.SelectionGrid;
import drawing_software.model.Shape;
import drawing_software.model.Vertex;
import drawing_software.view.CanvasView;
import drawing_software.view.menu.CopyMenuItem;
import drawing_software.view.menu.CutMenuItem;
import drawing_software.view.menu.DeleteMenuItem;
import drawing_software.view.menu.PasteMenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class SelectionTool implements Tool {
    private final CanvasView canvas;

    private final Invoker invoker;

    private Shape selectedShape;

    private Point2D oldShapeLocation;
    private Dimension oldShapeSize;

    private Point2D prevMouse;
    private Point2D startingPoint;

    private double ratio;

    public SelectionTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
        startingPoint = new Point2D.Double(0, 0);
    }

    /**
     * When pressing the mouse, this method checks if the user is pressing a valid shape,
     * then saves the current shape location and the  initial mouse position.
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseLeftPressed(MouseEvent mouseEvent) {
        mouseLeftClicked(mouseEvent);
        if(selectedShape != null){
            oldShapeLocation = selectedShape.getBounds().getLocation();
            prevMouse = mouseEvent.getPoint();
        }
        Point2D point = mouseEvent.getPoint();
        boolean found = false;
        Iterator<Drawable> itr = canvas.getDrawing().descendingIterator();
        if (canvas.getSelectionGrid() == null) {

            while (itr.hasNext()) {
                Shape s = (Shape) itr.next();
                if (s.contains(point)) {
                    this.selectedShape = s;
                    oldShapeSize = s.getBounds().getSize();
                    SelectionGrid grid = new SelectionGrid(s);
                    canvas.setSelectionGrid(grid);
                    ratio = grid.getWidth() / grid.getHeight();
                    prevMouse = mouseEvent.getPoint();
                    canvas.repaint();
                    found = true;
                    break;
                }
            }
        } else {
            while (itr.hasNext()) {
                Shape s = (Shape) itr.next();
                if (canvas.getSelectionGrid().getSelectedShape().equals(s) && canvas.getSelectionGrid().isVertexClicked(point)) {
                    SelectionGrid grid = new SelectionGrid(s);
                    canvas.setSelectionGrid(grid);
                    if (canvas.getSelectionGrid().isVertexClicked(point)) {
                        int v = canvas.getSelectionGrid().getSelectedVertex();
                        if (v != -1) {

                            if (v == Vertex.UPLEFT) {
                                startingPoint = new Point2D.Double(canvas.getSelectionGrid().getX() + canvas.getSelectionGrid().getWidth(), canvas.getSelectionGrid().getY() + canvas.getSelectionGrid().getHeight());
                            } else if (v == Vertex.UPRIGHT) {
                                startingPoint = new Point2D.Double(canvas.getSelectionGrid().getX(), canvas.getSelectionGrid().getY() + canvas.getSelectionGrid().getHeight());
                            } else if (v == Vertex.BOTTOMLEFT) {
                                startingPoint = new Point2D.Double(canvas.getSelectionGrid().getX() + canvas.getSelectionGrid().getWidth(), canvas.getSelectionGrid().getY());
                            } else if (v == Vertex.BOTTOMRIGHT) {
                                startingPoint = new Point2D.Double(canvas.getSelectionGrid().getX(), canvas.getSelectionGrid().getY());
                            }
                        }
                    }
                    canvas.repaint();
                    found = true;
                    break;
                }

            }
        }
        if (!found) {
            canvas.clearSelectedDrawable();
            canvas.repaint();
            selectedShape = null;
        }
    }

    /**
     * When dragging the mouse, this method checks if the user has pressed a valid shape,
     * then moves the shape and the selection grid according to the movement of the user.
     * The new position of the figure is given translating the location of the figure by a
     * vector (delta_x, delta_y), which components are given by the difference between the
     * coordinates between the current mouse position and the previous mouse position. The latter
     * is updated at the end of the method.
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if(selectedShape == null) return;
        int delta_x = (int) (mouseEvent.getX() - prevMouse.getX());
        int delta_y = (int) (mouseEvent.getY() - prevMouse.getY());

        selectedShape.setLocation(selectedShape.getBounds().getX() + delta_x, selectedShape.getBounds().getY() + delta_y);
        canvas.clearSelectedDrawable();
        canvas.setSelectionGrid(new SelectionGrid(selectedShape));
        prevMouse = mouseEvent.getPoint();
        canvas.repaint();

        if (canvas.getSelectionGrid() != null) {
            if (canvas.getSelectionGrid().getSelectedVertex() != -1) {                  //if a vertex is selected
                double x = min(startingPoint.getX(), mouseEvent.getX());
                double y = min(startingPoint.getY(), mouseEvent.getY());
                double width = 0, height = 0;
                if (Context.getInstance().isFixed()) {                                   //fixed proportions
                    height = abs(startingPoint.getY() - mouseEvent.getY());
                    width = height * ratio;
                } else {                                                                   //stretch
                    width = abs(startingPoint.getX() - mouseEvent.getX());
                    height = abs(startingPoint.getY() - mouseEvent.getY());
                }


                Shape shape = canvas.getSelectionGrid().getSelectedShape();
                shape.setFrame(new Point2D.Double(x, y), new Dimension((int) width, (int) height));
                canvas.getSelectionGrid().setFrame(new Point2D.Double(x, y), new Dimension((int) width, (int) height));
                canvas.repaint();

            }
        }
    }

    /**
     * When releasing the mouse, this method actually executes the move command, a concrete command
     * representing the update of the location of the figure.
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(selectedShape == null) return;
        canvas.getSelectionGrid().clearSelectedVertex();
        ratio = canvas.getSelectionGrid().getWidth() / canvas.getSelectionGrid().getHeight();
        invoker.executeCommand(new ResizeCommand(canvas, startingPoint, oldShapeSize));
        invoker.executeCommand(new MoveCommand(canvas, oldShapeLocation));
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
    }

}
