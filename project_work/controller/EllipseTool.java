package project_work.controller;

import project_work.model.DrawableEllipse;
import project_work.view.CanvasView;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class EllipseTool implements Tool {

    private DrawableEllipse ellipse;

    private final CanvasView canvas;

    private final Invoker invoker;

    private Point2D startingPoint;

    public EllipseTool(CanvasView canvas, Invoker invoker){
        this.canvas = canvas;
        this.invoker = invoker;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        double x = min(startingPoint.getX(), mouseEvent.getX());
        double y = min(startingPoint.getY(), mouseEvent.getY());
        double width = abs(startingPoint.getX() - mouseEvent.getX());
        double height = abs(startingPoint.getY() - mouseEvent.getY());

        ellipse.setFrame(x, y, width, height);
        canvas.setDummyDrawable(ellipse);
        canvas.repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        this.startingPoint = mouseEvent.getPoint();
        ellipse = new DrawableEllipse(mouseEvent.getX(), mouseEvent.getY());
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(ellipse.getWidth() > 0 && ellipse.getHeight() > 0){
            invoker.executeCommand(new ShapeCommand(canvas, ellipse));
        }

        canvas.clearDummyDrawable();
    }
}
