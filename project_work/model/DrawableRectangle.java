package project_work.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public class DrawableRectangle extends Rectangle2D.Double implements Drawable {
    private Paint fillColor;
    private Paint strokeColor;

    public DrawableRectangle(Paint fillColor, Paint strokeColor, double x, double y) {
        super(x, y, 0, 0);
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    public DrawableRectangle(double x, double y) {
        this(Color.white, Color.black, x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(fillColor);
        g2d.fill(this);
        g2d.setPaint(strokeColor);
        g2d.draw(this);
    }

    public void setSize(double w, double h) {
        this.setRect(this.getX(), this.getY(), w, h);
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }
}

