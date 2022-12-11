package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.ShapeCommand;
import drawing_software.model.DrawableLine;
import drawing_software.model.DrawablePolygon;
import drawing_software.view.CanvasView;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PolygonTool implements Tool {
    private DrawablePolygon dp;
    private DrawableLine line;
    private final CanvasView canvas;

    private final Invoker invoker;
    private ArrayList<Point2D> points;
    int[] allX;
    int[] allY;
    private Point2D point;

    public PolygonTool(CanvasView canvas, Invoker invoker) {
        points = new ArrayList<>();
        this.canvas = canvas;
        this.invoker = invoker;
        canvas.clearSelectedDrawable();
        canvas.repaint();

    }

    @Override
    public void mouseLeftPressed(MouseEvent mouseEvent) {
        if (!points.contains(mouseEvent.getPoint()))
            points.add(mouseEvent.getPoint());
        dp = new DrawablePolygon(points, canvas.getCurrentFillColor(), canvas.getCurrentStrokeColor(), allX, allY);
        canvas.setDummyDrawable(dp);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (!points.isEmpty()) {
            if (line != null)
                canvas.setDummyDrawable(dp);
            canvas.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Point2D intorno = new Point2D.Double(points.get(0).getX() - 30, points.get(0).getY() - 30);
        Rectangle2D r = new Rectangle2D.Double(intorno.getX(), intorno.getY(), 60, 60);
        if ((r.contains(mouseEvent.getPoint()) && points.size() == 1) || (!r.contains(mouseEvent.getPoint()) && points.size() != 1)) { //the polygon is still open
           /* invoker.executeCommand(new ShapeCommand(canvas, line));
            line = new DrawableLine(canvas.getCurrentStrokeColor(), mouseEvent.getPoint(), mouseEvent.getPoint());
            */
        } else {
            allX = new int[points.size()];
            allY = new int[points.size()];
            int i = 0;
            for (Point2D p : points) {
                allX[i] = (int) p.getX();
                allY[i] = (int) p.getY();
                i++;
            }
            invoker.executeCommand(new ShapeCommand(canvas, dp));
        }
        canvas.clearDummyDrawable();
    }

}
