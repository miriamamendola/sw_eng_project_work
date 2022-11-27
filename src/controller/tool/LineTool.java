package src.controller.tool;

import src.controller.command.Invoker;
import src.controller.command.ShapeCommand;
import src.model.DrawableLine;
import src.view.CanvasView;

import java.awt.event.MouseEvent;
/**
 * Defines the behaviour of the canvas when the Line Tool is selected.
 * When this state is selected, the user can draw a line on the canvas by dragging the mouse.
 */
public class LineTool implements Tool {

    private DrawableLine line;

    private final CanvasView canvas;

    private final Invoker invoker;

    public LineTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
        canvas.clearSelectedDrawable();
        canvas.repaint();
    }

    /**
     * Defines how the canvas reacts when holding the mouse button down.
     * It creates the line according to the mouse coordinates and the current color.
     *
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        line = new DrawableLine(canvas.getCurrentStrokeColor(), mouseEvent.getPoint(), mouseEvent.getPoint());
        canvas.setDummyDrawable(line);
    }

    /**
     * Defines how the canvas reacts to dragging the mouse.
     * The line is rendered according to the direction of the dragging.
     *
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        line.setLine(line.getP1(), mouseEvent.getPoint());
        canvas.repaint();
    }

    /**
     * Defines how the canvas reacts when releasing the mouse button.
     * Prevents creating a new line when it has zero length.
     *
     * @param mouseEvent the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (!line.getP1().equals(line.getP2())) {
            invoker.executeCommand(new ShapeCommand(canvas, line));
        }
        canvas.clearDummyDrawable();
    }

}
