package project_work.model;

import java.awt.*;

/**
 * A shape per-se, allows to mark the selection of the selected shape.
 */
public class SelectionGrid extends DrawableRectangle {
    private Shape selectedShape;
    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);

    /**
     * Allows to actually create the selection grid by calculating the dimensions of the selected shape and,
     * through them, define the width and height of the grid so that it just encompassed the selected shape.
     * When a fill color is not purposefully selected, the shape will be transparent on the inside.
     * @param selectedShape is the selected shape that's intended to be highlighted.
     */
    public SelectionGrid(Shape selectedShape) {
        super(null, Color.gray, selectedShape.getBounds().getX(), selectedShape.getBounds().getY());
        this.setRect(selectedShape.getBounds().getX() - 1, selectedShape.getBounds().getY() - 1, selectedShape.getBounds().getWidth() + 2, selectedShape.getBounds().getHeight() + 2);
        this.selectedShape = selectedShape;
    }

    /**
     * Allows to draw the selection grid, having previously set the style of the stroke,
     * by calling the parent method and passing the Graphics2D object.
     * @param g2d is the Graphics2D object that is going to be painted on the canvas.
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(dashed);
        super.draw(g2d);
    }

    /**
     * Getter method for the selectedShape property.
     * @return the actual selected shape.
     */
    public Shape getSelectedShape() {
        return selectedShape;
    }

    /**
     * Setter method for the selectedShape property.
     * @param selectedShape is the highlighted shape.
     */
    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
    }
}
