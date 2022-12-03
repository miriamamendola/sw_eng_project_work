package drawing_software.model;

import java.awt.*;
import java.awt.geom.Point2D;


public class Vertex extends DrawableRectangle {

    private int vertexType;
    public static final int UPLEFT = 0, UPRIGHT = 1, BOTTOMLEFT = 2, BOTTOMRIGHT = 3;

    private static final int offsetVertex = 4;
    private static final Dimension sizeVertex = new Dimension(offsetVertex * 2, offsetVertex * 2);
    private static final int hitBoxVertex = offsetVertex * 8;

    public Vertex(DrawableRectangle rect, int vertexType) {
        super(0, 0);
        this.vertexType = vertexType;
        Point2D point = new Point2D.Double(0, 0);
        if (vertexType == UPLEFT) {
            point = new Point2D.Double(rect.getX() - offsetVertex, rect.getY() - offsetVertex);
        } else if (vertexType == UPRIGHT) {
            point = new Point2D.Double(rect.getX() + rect.getWidth() - offsetVertex, rect.getY() - offsetVertex);
        } else if (vertexType == BOTTOMLEFT) {
            point = new Point2D.Double(rect.getX() - offsetVertex, rect.getY() + rect.getHeight() - offsetVertex);

        } else if (vertexType == BOTTOMRIGHT) {
            point = new Point2D.Double(rect.getX() + rect.getWidth() - offsetVertex, rect.getY() + rect.getHeight() - offsetVertex);
        }

        this.setFrame(point, sizeVertex);
    }


    private void setLargeHitBox() {
        this.setRect(this.getX() - 12, this.getY() - 12, hitBoxVertex, hitBoxVertex);
    }

    private void setStandardHitBox() {
        this.setRect(this.getX() + 12, this.getY() + 12, sizeVertex.getWidth(), sizeVertex.getHeight());
    }


    @Override
    public boolean contains(Point2D point) {
        this.setLargeHitBox();
        if (super.contains(point)) {
            setStandardHitBox();
            return true;
        } else {
            setStandardHitBox();
            return false;
        }
    }


}
