package project_work.model;

import java.awt.*;

public class SelectionGrid extends DrawableRectangle {
    private Shape selectedShape;
    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);

    public SelectionGrid(Shape selectedShape) {
        super(null, Color.gray, selectedShape.getBounds().getX(), selectedShape.getBounds().getY());
        this.setRect(selectedShape.getBounds().getX() - 1, selectedShape.getBounds().getY() - 1, selectedShape.getBounds().getWidth() + 2, selectedShape.getBounds().getHeight() + 2);
        this.selectedShape = selectedShape;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(dashed);
        super.draw(g2d);
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
    }
}
