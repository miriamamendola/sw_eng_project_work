package drawing_software.model;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Shape extends Drawable {

    boolean contains(Point2D point);

    void setFrame(Point2D point, Dimension size);

    void setLocation(Point2D point);

    void setSize(Dimension size);

    Rectangle getBounds();
}
