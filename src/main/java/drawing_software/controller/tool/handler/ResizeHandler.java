package drawing_software.controller.tool.handler;

import drawing_software.Context;
import drawing_software.controller.tool.MouseRequest;
import drawing_software.model.SelectionGrid;
import drawing_software.model.Shape;
import drawing_software.model.Vertex;
import drawing_software.view.Canvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class ResizeHandler extends Handler {

    public ResizeHandler(Canvas canvas) {
        super(canvas);
    }

    @Override
    void handlePressed(MouseRequest request) {
        Point2D startingPoint = null;
        SelectionGrid grid = new SelectionGrid(request.getSelectedShape());
        canvas.setSelectionGrid(grid);

        if (grid.isVertexClicked(request.getMouseEvent().getPoint())) {
            int v = grid.getSelectedVertex();
            if (v != -1) {
                Rectangle r = grid.getSelectedShape().getBounds();
                if (v == Vertex.UPLEFT) {
                    startingPoint = new Point2D.Double(r.getX() + r.getWidth(), r.getY() + r.getHeight());
                } else if (v == Vertex.UPRIGHT) {
                    startingPoint = new Point2D.Double(r.getX(), r.getY() + r.getHeight());
                } else if (v == Vertex.BOTTOMLEFT) {
                    startingPoint = new Point2D.Double(r.getX() + r.getWidth(), r.getY());
                } else if (v == Vertex.BOTTOMRIGHT) {
                    startingPoint = new Point2D.Double(r.getX(), r.getY());
                }
                request.setStartingPoint(startingPoint);
            }
        }

        canvas.repaint();
    }

    @Override
    void handleDragged(MouseRequest request) {

        MouseEvent mouseEvent = request.getMouseEvent();
        Point2D startingPoint = request.getStartingPoint();

        double x = min(startingPoint.getX(), mouseEvent.getX());
        double y = min(startingPoint.getY(), mouseEvent.getY());
        double width, height;
        if (Context.getInstance().isFixed()) {                                   //fixed proportions - resize
            height = abs(startingPoint.getY() - mouseEvent.getY());
            width = height * request.getRatio();
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
