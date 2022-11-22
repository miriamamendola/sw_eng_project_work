package model;

import java.awt.*;
import java.awt.geom.Line2D;

public class DrawableLine extends Line2D.Double implements Drawable  {
    private Paint strokeColor;

    public DrawableLine(Paint strokeColor) {
        this.strokeColor = strokeColor;
    }

    public DrawableLine() {
        this.strokeColor = Color.black;
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(strokeColor);
        g2d.draw(this);
    }
}
