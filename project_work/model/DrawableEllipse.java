package project_work.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class DrawableEllipse extends Ellipse2D.Double implements Drawable{
    private Paint fillColor;
    private Paint strokeColor;

    public DrawableEllipse(Paint fillColor, Paint strokeColor, double x, double y) {
        super(x,y,0,0);
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    public DrawableEllipse(double x, double y) {
        this(Color.white, Color.black, x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setPaint(fillColor);
        g2d.fill(this);
        g2d.setPaint(strokeColor);
        g2d.draw(this);
    }
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    public void setSize(double w, double h){
        this.setFrame(this.getX(), this.getY(), w, h);
    }
}
