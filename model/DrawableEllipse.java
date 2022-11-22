package model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class DrawableEllipse extends Ellipse2D.Double implements Drawable{
    private Paint fillColor;
    private Paint strokeColor;

    public DrawableEllipse(Paint fillColor, Paint strokeColor) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    public DrawableEllipse() {
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
