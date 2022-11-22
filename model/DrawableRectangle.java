package model;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class DrawableRectangle extends Rectangle2D.Double implements Drawable {
    private Paint fillColor;
    private Paint strokeColor;

    public DrawableRectangle(Paint fillColor, Paint strokeColor) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    public DrawableRectangle() {
        this.fillColor = Color.white;
        this.strokeColor = Color.black;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(fillColor);
        g2d.fill(this);
        g2d.setPaint(strokeColor);
        g2d.draw(this);
    }
}
