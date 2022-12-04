package drawing_software.controller.command;

import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.geom.Point2D;

public class ResizeCommand implements Command {

    private final CanvasView canvas;
    private final Point2D oldShapeLocation;
    private final Dimension oldShapeSize;

    public ResizeCommand(CanvasView canvas, Point2D oldShapeLocation, Dimension oldShapeSize) {
        this.canvas = canvas;
        this.oldShapeLocation = oldShapeLocation;
        this.oldShapeSize = oldShapeSize;
    }


    @Override
    public void execute() {

    }
}
