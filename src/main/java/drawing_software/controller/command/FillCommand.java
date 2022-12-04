package drawing_software.controller.command;

import drawing_software.model.Shape;
import drawing_software.view.CanvasView;
import drawing_software.view.colors.ColorButton;

import javax.swing.*;
import java.awt.*;

public class FillCommand implements Command {
    private final CanvasView canvas;
    private final ColorButton source;

    private Color previousShapeColor;

    private Shape modifiedShape;


    public FillCommand(CanvasView canvas, ColorButton source) {
        this.canvas = canvas;
        this.source = source;
    }

    @Override
    public void execute() {
        Color firstColor = canvas.getCurrentFillColor();
        Color color = JColorChooser.showDialog(canvas, "Select color", firstColor);
        if (canvas.getSelectionGrid() != null) {
            modifiedShape = canvas.getSelectionGrid().getSelectedShape();
            previousShapeColor = (Color) modifiedShape.getFillColor();
            modifiedShape.setFillColor(color);
            source.changeColor((Color) modifiedShape.getFillColor());
            canvas.repaint();
        } else {
            source.changeColor(color);
            canvas.setCurrentFillColor(color);
        }
    }
}
