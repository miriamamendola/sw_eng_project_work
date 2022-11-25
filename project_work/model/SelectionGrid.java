package project_work.model;

import java.awt.*;

public class SelectionGrid extends DrawableRectangle {
    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);

    public SelectionGrid(Shape selectedShape) {
        super(null, Color.blue, selectedShape.getBounds().getX(), selectedShape.getBounds().getY());
        this.setRect(selectedShape.getBounds().getX()-1, selectedShape.getBounds().getY()-1, selectedShape.getBounds().getWidth()+2, selectedShape.getBounds().getHeight()+2);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(dashed);
        g2d.setPaint(Color.gray);
        g2d.draw(this);
    }
}
