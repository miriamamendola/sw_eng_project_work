package drawing_software.model;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Shape extends Drawable {

    boolean contains(Point2D point);

    void setFrame(Point2D point, Dimension size);

    void setLocation(Point2D point);

    default void setLocation(double x, double y){
        this.setLocation(new Point2D.Double(x,y));
    }

    void setSize(Dimension size);

    Rectangle getBounds();

    void setStrokeColor(Paint color);

    Paint getStrokeColor();

    default void setFillColor(Paint color){}
    default Paint getFillColor(){
        return null;
    }
}
