package drawing_software.model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * A shape per-se, allows to mark the selection of the selected shape.
 */
public class SelectionGrid extends DrawableRectangle {
    private Shape selectedShape;

    public class Vertex {
        DrawableRectangle upLeft;
        DrawableRectangle upRight;
        DrawableRectangle bottomLeft;
        DrawableRectangle bottomRight;
        DrawableRectangle selectedVertex;

        int offset = 4;
        Dimension size = new Dimension(offset * 2, offset * 2);
        Dimension hitBox = new Dimension(offset * 4, offset * 4);

        public Vertex(DrawableRectangle rect) {

            Point2D pointUpLeft = new Point2D.Double(rect.getX() - offset, rect.getY() - offset);
            Point2D pointUpRight = new Point2D.Double(rect.getX() + rect.getWidth() - offset, rect.getY() - offset);
            Point2D pointBottomLeft = new Point2D.Double(rect.getX() - offset, rect.getY() + rect.getHeight() - offset);
            Point2D pointBottomRight = new Point2D.Double(rect.getX() + rect.getWidth() - offset, rect.getY() + rect.getHeight() - offset);


            upLeft = new DrawableRectangle(pointUpLeft.getX(), pointUpLeft.getY());
            upLeft.setSize(size);
            upRight = new DrawableRectangle(pointUpRight.getX(), pointUpRight.getY());
            upRight.setSize(size);
            bottomLeft = new DrawableRectangle(pointBottomLeft.getX(), pointBottomLeft.getY());
            bottomLeft.setSize(size);
            bottomRight = new DrawableRectangle(pointBottomRight.getX(), pointBottomRight.getY());
            bottomRight.setSize(size);
        }

        public DrawableRectangle getUpLeft() {
            return upLeft;
        }

        public void setUpLeft(DrawableRectangle upLeft) {
            this.upLeft = upLeft;
        }

        public DrawableRectangle getUpRight() {
            return upRight;
        }

        public void setUpRight(DrawableRectangle upRight) {
            this.upRight = upRight;
        }

        public DrawableRectangle getBottomLeft() {
            return bottomLeft;
        }

        public void setBottomLeft(DrawableRectangle bottomLeft) {
            this.bottomLeft = bottomLeft;
        }

        public DrawableRectangle getBottomRight() {
            return bottomRight;
        }

        public void setBottomRight(DrawableRectangle bottomRight) {
            this.bottomRight = bottomRight;
        }

        public void draw(Graphics2D g2d) {
            upLeft.draw(g2d);
            upRight.draw(g2d);
            bottomLeft.draw(g2d);
            bottomRight.draw(g2d);
        }

        public boolean contains(Point2D point) {
            //increase hitbox
            upLeft.setSize(hitBox);
            upRight.setSize(hitBox);
            bottomLeft.setSize(hitBox);
            bottomRight.setSize(hitBox);
            if (upLeft.contains(point)) {
                upLeft.setSize(size);
                upRight.setSize(size);
                bottomLeft.setSize(size);
                bottomRight.setSize(size);
                selectedVertex = upLeft;
                return true;
            } else if (upRight.contains(point)) {
                upLeft.setSize(size);
                upRight.setSize(size);
                bottomLeft.setSize(size);
                bottomRight.setSize(size);
                selectedVertex = upRight;
                return true;
            } else if (bottomLeft.contains(point)) {
                upLeft.setSize(size);
                upRight.setSize(size);
                bottomLeft.setSize(size);
                bottomRight.setSize(size);
                selectedVertex = bottomLeft;
                return true;
            } else if (bottomRight.contains(point)) {
                upLeft.setSize(size);
                upRight.setSize(size);
                bottomLeft.setSize(size);
                bottomRight.setSize(size);
                selectedVertex = bottomRight;
                return true;
            } else {
                upLeft.setSize(size);
                upRight.setSize(size);
                bottomLeft.setSize(size);
                bottomRight.setSize(size);
                selectedVertex = null;
                return false;
            }
        }

        public DrawableRectangle getSelectedVertex() {
            return selectedVertex;
        }
    }

    private Vertex vertex;

    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);

    /**
     * Allows to actually create the selection grid by calculating the dimensions of the selected shape and,
     * through them, define the width and height of the grid so that it just encompassed the selected shape.
     * When a fill color is not purposefully selected, the shape will be transparent on the inside.
     *
     * @param selectedShape is the selected shape that's intended to be highlighted.
     */
    public SelectionGrid(Shape selectedShape) {
        super(null, Color.gray, selectedShape.getBounds().getX(), selectedShape.getBounds().getY());
        this.setRect(selectedShape.getBounds().getX() - 1, selectedShape.getBounds().getY() - 1, selectedShape.getBounds().getWidth() + 2, selectedShape.getBounds().getHeight() + 2);
        this.selectedShape = selectedShape;
        this.vertex = new Vertex(this);
    }

    /**
     * Allows to draw the selection grid, having previously set the style of the stroke,
     * by calling the parent method and passing the Graphics2D object.
     * @param g2d is the Graphics2D object that is going to be painted on the canvas.
     */
    @Override
    public void draw(Graphics2D g2d) {
        vertex.draw(g2d);
        g2d.setStroke(dashed);
        super.draw(g2d);
    }

    /**
     * Getter method for the selectedShape property.
     *
     * @return the actual selected shape.
     */
    public Drawable getSelectedShape() {
        return selectedShape;
    }

    public Vertex getVertex() {
        return vertex;
    }
}
