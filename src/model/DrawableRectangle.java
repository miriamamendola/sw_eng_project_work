package src.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Defines the actual rectangle shape and it's properties.
 */
public class DrawableRectangle extends Rectangle2D.Double implements Drawable {
    private Paint fillColor;
    private Paint strokeColor;

    /**
     * Creates a rectangle whose properties will be set to the input parameters' values.
     * @param fillColor is the internal color of the shape.
     * @param strokeColor is the outline color of the rectangle.
     * @param x is the first coordinate used to determine the position on the canvas.
     * @param y is the second coordinate used to determine the position on the canvas.
     */

    public DrawableRectangle(Paint fillColor, Paint strokeColor, double x, double y) {
        super(x, y, 0, 0);
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    /**
     * Creates a rectangle in which the fillColor and strokeColor properties will be set to default values,
     * which are TRANSPARENT for the fill and black for the stroke.
     * @param x is the first coordinate used to determine the position on the canvas.
     * @param y is the second coordinate used to determine the position on the canvas.
     */
    public DrawableRectangle(double x, double y) {
        this(null, Color.black, x, y);
    }

    /**
     * Allows to draw che input parameter object by calling the parent's draw method.
     * If the default constructor is used, and the fill is to be transparent, then there is no need
     * to call the fill() method at all.
     * @param g2d is the Graphics2D object to be drawn.
     */
    @Override
    public void draw(Graphics2D g2d) {
        if(fillColor != null) {
            g2d.setPaint(fillColor);
            g2d.fill(this);
        }
        g2d.setPaint(strokeColor);
        g2d.draw(this);
    }
    /**
     * Allows to verify if an input object and a DrawableRectangle one are actually
     * equivalent to each other. Used in JUnit testing.
     *
     * @param o an {@code Object} to be compared with this
     *          {@code Rectangle2D}.
     * @return whether the input object is actually equal to the DrawableRectangle object.
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }
}

