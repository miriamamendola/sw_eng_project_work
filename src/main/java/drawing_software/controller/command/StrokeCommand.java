package drawing_software.controller.command;

import drawing_software.model.Shape;
import drawing_software.view.CanvasView;
import drawing_software.view.colors.ColorButton;

import javax.swing.*;
import java.awt.*;

public class StrokeCommand implements Command {

    private final CanvasView canvas;

    private final ColorButton source;

    private Color previousStrokeColor;

    private Shape modifiedShape;

    public StrokeCommand(CanvasView canvas, ColorButton source) {
        this.canvas = canvas;
        this.source = source;
    }

    @Override
    public void execute() {
        updateTitle(canvas);
        Color firstColor = canvas.getCurrentStrokeColor();
        Color color = JColorChooser.showDialog(canvas, "Select color", firstColor);
        if (canvas.getSelectionGrid() != null) {
            modifiedShape = canvas.getSelectionGrid().getSelectedShape();
            previousStrokeColor = (Color) modifiedShape.getStrokeColor();
            modifiedShape.setStrokeColor(color);
            source.changeColor((Color) modifiedShape.getStrokeColor());
            canvas.repaint();
        } else {
            source.changeColor(color);
            canvas.setCurrentStrokeColor(color);
        }
    }
}

