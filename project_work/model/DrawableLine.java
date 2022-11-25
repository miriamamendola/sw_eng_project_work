package project_work.model;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class DrawableLine extends Line2D.Double implements Drawable  {
    private Paint strokeColor;
    private static final int HIT_BOX_SIZE = 5;
    public DrawableLine(Paint strokeColor, Point2D p1, Point2D p2) {
        super(p1, p2);
        this.strokeColor = strokeColor;
    }

    public DrawableLine(Point2D p1, Point2D p2) {
        this(Color.black, p1, p2);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(strokeColor);
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(this);
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public boolean contains(Point2D point){
        double boxX = point.getX() - HIT_BOX_SIZE / 2;
        double boxY = point.getY() - HIT_BOX_SIZE / 2;

        int width = HIT_BOX_SIZE;
        int height = HIT_BOX_SIZE;

        return this.intersects(boxX, boxY, width, height);
        }

}
