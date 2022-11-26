package project_work.controller.tool;

import project_work.controller.command.Invoker;
import project_work.controller.command.ShapeCommand;
import project_work.model.DrawableRectangle;
import project_work.view.CanvasView;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class RectangleTool implements Tool {

    private DrawableRectangle rect;

    private final CanvasView canvas;

    private final Invoker invoker;

    private Point2D startingPoint;

    public RectangleTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
        canvas.clearSelectedDrawable();
        canvas.repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        this.rect = new DrawableRectangle(mouseEvent.getX(), mouseEvent.getY());
        this.startingPoint = mouseEvent.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        double x = min(startingPoint.getX(), mouseEvent.getX());
        double y = min(startingPoint.getY(), mouseEvent.getY());
        double width = abs(startingPoint.getX() - mouseEvent.getX());
        double height = abs(startingPoint.getY() - mouseEvent.getY());

        rect.setRect(x, y, width, height);
        canvas.setDummyDrawable(rect);
        canvas.repaint();
    }


    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (rect.getWidth() > 0 && rect.getHeight() > 0) {
            invoker.executeCommand(new ShapeCommand(canvas, rect));
        }
        canvas.clearDummyDrawable();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

}
