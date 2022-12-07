package drawing_software.controller.command;

import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import java.awt.*;

/**
 * Implements a ConcreteCommand class; in this case, the Command is the Fill command.
 */
public class FillCommand implements Command {
    private final CanvasView canvas;
    private final Color color;

    private Color previousColor;

    private Shape modifiedShape;


    public FillCommand(CanvasView canvas, Color color) {
        this.canvas = canvas;
        this.color = color;
    }

    /**
     * Updates the title of the window. Then, if the user has selected a shape, changes the shape color
     * its color accordingly to the color chosen. If no shape is selected, it changes the
     * canvas color for filling new shapes.
     */
    @Override
    public void execute() {
        updateTitle(canvas);

        if (canvas.getSelectionGrid() != null) {
            modifiedShape = canvas.getSelectionGrid().getSelectedShape();
            previousColor = (Color) modifiedShape.getFillColor();
            modifiedShape.setFillColor(color);
            canvas.repaint();
        } else {
            previousColor = canvas.getCurrentFillColor();
            canvas.setCurrentFillColor(color);
        }
    }

    @Override
    public void undo() {
        if (modifiedShape == null) {
            canvas.setCurrentFillColor(previousColor);
        } else {
            modifiedShape.setFillColor(previousColor);
        }
        canvas.repaint();
    }
}
