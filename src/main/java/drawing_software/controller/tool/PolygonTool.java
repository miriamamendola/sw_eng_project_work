package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.ShapeCommand;
import drawing_software.model.Drawable;
import drawing_software.model.DrawableLine;
import drawing_software.model.DrawablePolygon;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PolygonTool implements Tool {
    private DrawablePath dp = new DrawablePath();
    private DrawableLine line;
    private final CanvasView canvas;

    private final Invoker invoker;
    private ArrayList<Point2D> points;
    int[] allX;
    int[] allY;
    private Point2D point;

    private boolean closed = false;

    public PolygonTool(CanvasView canvas, Invoker invoker) {
        points = new ArrayList<>();
        this.canvas = canvas;
        this.invoker = invoker;
        canvas.clearSelectedDrawable();
        canvas.repaint();

    }

    class DrawablePath extends Path2D.Double implements Drawable {

        @Override
        public void draw(Graphics2D g2d) {
            g2d.setPaint(Color.black);
            g2d.draw(this);
        }

    }

    @Override
    public void mouseLeftPressed(MouseEvent mouseEvent) {

        if (points.isEmpty()) {
            dp = new DrawablePath();
            dp.moveTo(mouseEvent.getX(), mouseEvent.getY());
            canvas.setDummyDrawable(dp);
            points.add(mouseEvent.getPoint());
        } else {
            dp.lineTo(mouseEvent.getX(), mouseEvent.getY());
            Point2D intorno = new Point2D.Double(points.get(0).getX() - 30, points.get(0).getY() - 30);
            Rectangle2D r = new Rectangle2D.Double(intorno.getX(), intorno.getY(), 60, 60);
            if (!r.contains(mouseEvent.getPoint())) {
                points.add(mouseEvent.getPoint());
            } else {
                System.out.println(points.size());
                closed = true;
            }
        }
        canvas.repaint();


    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (!points.isEmpty()) {
            canvas.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (closed) {
            allX = new int[points.size()];
            allY = new int[points.size()];
            int i = 0;
            for (Point2D p : points) {
                allX[i] = (int) p.getX();
                allY[i] = (int) p.getY();
                i++;
            }
            DrawablePolygon polygon = new DrawablePolygon(points, canvas.getCurrentFillColor(), canvas.getCurrentStrokeColor(), allX, allY);
            invoker.executeCommand(new ShapeCommand(canvas, polygon));
            canvas.clearDummyDrawable();
        }

    }

}
