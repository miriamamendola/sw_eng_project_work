package drawing_software.controller.tool;

import drawing_software.model.Drawable;
import drawing_software.model.SelectionGrid;
import drawing_software.view.CanvasView;

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
    public void mouseClicked(MouseEvent mouseEvent) {

        Point2D point = mouseEvent.getPoint();
        boolean found = false;
        Iterator<Drawable> itr = canvas.getDrawing().descendingIterator();
        while (itr.hasNext()) {
            Shape s = (Shape) itr.next();
            if (s.contains(point)) {
                canvas.setSelectionGrid(new SelectionGrid(s));
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
