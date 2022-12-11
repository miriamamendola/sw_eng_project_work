package drawing_software.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DrawablePolygon extends Polygon implements Shape {
    private ArrayList<Point2D> points; // holds the points of the polygon
    private Rectangle bounds;
    private Paint fillColor;
    private Paint strokeColor;

    public DrawablePolygon(ArrayList<Point2D> points, Paint fillColor, Paint strokeColor, int[] allX, int[] allY) {
        super(allX, allY, points.size());
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.bounds = super.getBounds();
        this.points = points;
    }

    public void draw(Graphics2D g2d) {
        if (points.size() > 1) {
            // draw the lines of the polygon
            for (int i = 0; i < points.size() - 1; i++) {
                double x1 = points.get(i).getX();
                double y1 = points.get(i).getY();
                double x2 = points.get(i + 1).getX();
                double y2 = points.get(i + 1).getY();
                DrawableLine line1 = new DrawableLine(points.get(i), points.get(i + 1));
                line1.draw(g2d);
            }
            // draw the last line connecting the last point to the first point
            double x1 = points.get(points.size() - 1).getX();
            double y1 = points.get(points.size() - 1).getY();
            double x2 = points.get(0).getX();
            double y2 = points.get(0).getY();
            Point2D p1 = new Point2D.Double(x1, y1);
            Point2D p2 = new Point2D.Double(x2, y2);
            DrawableLine line2 = new DrawableLine(p1, p2);
            line2.draw(g2d);
            if (fillColor != null) {
                g2d.setPaint(fillColor);
                g2d.fill(this);
            }
            g2d.setPaint(strokeColor);
            g2d.draw(this);
        }
    }

    public void addPoint(int x, int y) {
        points.add(new Point2D.Double(x, y));
    }

    @Override
    public void setFrame(Point2D point, Dimension size) {
        super.bounds = bounds;
    }

    @Override
    public void setLocation(Point2D point) {

    }

    @Override
    public void setSize(Dimension size) {
        super.bounds.setSize(size);
    }

    @Override
    public Shape clone() {
        try {
            DrawablePolygon clone = (DrawablePolygon) super.clone();
            clone.setFillColor(this.fillColor);
            clone.setStrokeColor(this.strokeColor);
            clone.setBounds(this.bounds);
            clone.setPoints(points);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setStrokeColor(Paint color) {
        this.strokeColor = color;
    }

    @Override
    public Paint getStrokeColor() {
        return strokeColor;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point2D> points) {
        this.points = points;
    }
}
