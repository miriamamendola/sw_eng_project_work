package project_work.controller;

import project_work.model.Drawable;
import project_work.model.SelectionGrid;
import project_work.view.CanvasView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

public class SelectionTool implements Tool {
    private final CanvasView canvas;

    public SelectionTool(CanvasView canvas) {
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        Point2D point = mouseEvent.getPoint();
        boolean found = false;
        Iterator<Drawable> itr = canvas.getDrawing().descendingIterator();
        while (itr.hasNext()) {
            Shape s = (Shape) itr.next();
            if (s.contains(point)) {
                canvas.setSelectedDrawable(new SelectionGrid(s));
                canvas.repaint();
                found = true;
                break;
            }
        }
        if (!found) {
            canvas.clearSelectedDrawable();
            canvas.repaint();
        }

    }
}
