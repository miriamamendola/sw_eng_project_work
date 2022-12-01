package drawing_software.model;

import java.awt.*;
import java.awt.geom.Point2D;

public class Vertex {
    DrawableRectangle upLeft;
    DrawableRectangle upRight;
    DrawableRectangle bottomLeft;
    DrawableRectangle bottomRight;
    DrawableRectangle selectedVertex;

    int offset = 4;
    Dimension size = new Dimension(offset * 2, offset * 2);
    int hitBox = offset * 8;


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

    private void setLargeHitBox() {
        upLeft.setRect(upLeft.getX() - 12, upLeft.getY() - 12, hitBox, hitBox);
        upRight.setRect(upRight.getX() - 12, upRight.getY() - 12, hitBox, hitBox);
        bottomLeft.setRect(bottomLeft.getX() - 12, bottomLeft.getY() - 12, hitBox, hitBox);
        bottomRight.setRect(bottomRight.getX() - 12, bottomRight.getY() - 12, hitBox, hitBox);
    }

    private void setStandardHitBox() {
        upLeft.setRect(upLeft.getX() + 12, upLeft.getY() + 12, size.getWidth(), size.getHeight());
        upRight.setRect(upRight.getX() + 12, upRight.getY() + 12, size.getWidth(), size.getHeight());
        bottomLeft.setRect(bottomLeft.getX() + 12, bottomLeft.getY() + 12, size.getWidth(), size.getHeight());
        bottomRight.setRect(bottomRight.getX() + 12, bottomRight.getY() + 12, size.getWidth(), size.getHeight());
    }

    public boolean contains(Point2D point) {
        this.setLargeHitBox();
        if (upLeft.contains(point)) {
            setStandardHitBox();
            selectedVertex = upLeft;
            return true;
        } else if (upRight.contains(point)) {
            setStandardHitBox();
            selectedVertex = upRight;
            return true;
        } else if (bottomLeft.contains(point)) {
            setStandardHitBox();
            selectedVertex = bottomLeft;
            return true;
        } else if (bottomRight.contains(point)) {
            setStandardHitBox();
            selectedVertex = bottomRight;
            return true;
        } else {
            setStandardHitBox();
            selectedVertex = null;
            return false;
        }
    }

    public DrawableRectangle getSelectedVertex() {
        return selectedVertex;
    }
}
