package drawing_software.model;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class DrawablePolygon extends Polygon implements Shape {
    private ArrayList<Point2D> points; // holds the points of the polygon
    private Rectangle bounds;
    private Paint fillColor;
    private Paint strokeColor;

    private int[] allX;

    private int[] allY;

    public DrawablePolygon(ArrayList<Point2D> points, Paint fillColor, Paint strokeColor, int[] allX, int[] allY) {
        super(allX, allY, points.size());
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.bounds = super.getBounds();
        this.points = points;
    }

    public void draw(Graphics2D g2d) {
        g2d.setPaint(fillColor);
        g2d.fill(this);
        g2d.setPaint(strokeColor);
        g2d.draw(this);

    }

    public void addInternalPoint(int x, int y) {
        points.add(new Point2D.Double(x, y));
    }

    @Override
    public void setFrame(Point2D point, Dimension size) {
        double ratioX = (double) size.width / bounds.width;
        double ratioY = (double) size.height / bounds.height;
        System.out.println(ratioX);
        System.out.println(ratioY);
        AffineTransform at = new AffineTransform();
        at.scale(ratioX, ratioY);
        Path2D.Double polygon = (Path2D.Double) at.createTransformedShape(this);
        ArrayList<Point2D> points = new ArrayList<>();
        this.reset();

        PathIterator iterator = polygon.getPathIterator(null);
        while (!iterator.isDone()) {
            double segment[] = new double[6];
            iterator.currentSegment(segment);
            System.out.println(Arrays.toString(segment));
            if (segment[0] != 0 && segment[1] != 0) {
                this.addPoint((int) segment[0], (int) segment[1]);
            }
            iterator.next();
        }

        /*for(int i = 0; i < polygon.npoints; i++){

             points.add(new Point2D.Double(polygon.xpoints[i], polygon.ypoints[i]));
             this.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
        }
        this.setPoints(points);*/

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

    public int[] getAllX() {
        return allX;
    }

    public void setAllX(int[] allX) {
        this.allX = allX;
    }

    public int[] getAllY() {
        return allY;
    }

    public void setAllY(int[] allY) {
        this.allY = allY;
    }

    @Override
    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
    }
}
