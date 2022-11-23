package project_work.model;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class DrawableLine extends Line2D.Double implements Drawable  {
    private Paint strokeColor;

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
        g2d.draw(this);
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }
}
