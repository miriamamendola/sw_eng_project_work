package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.model.Drawable;
import drawing_software.model.SelectionGrid;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

public class SelectionTool implements Tool {
    private final CanvasView canvas;
    private Invoker invoker;

    public SelectionTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        Point2D point = mouseEvent.getPoint();
        boolean found = false;
        Iterator<Drawable> itr = canvas.getDrawing().descendingIterator();
        while (itr.hasNext()) {
            Drawable d = itr.next();
            Shape s = (Shape) d;
            if (s.contains(point)) {
                canvas.setSelectionGrid(new SelectionGrid(d));
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

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("a");
        if (KeyEvent.VK_DELETE == keyEvent.getKeyChar() || KeyEvent.VK_DELETE == keyEvent.getKeyCode()) {
            //canvas.getDrawing().removeDrawable(canvas.getSelectedDrawable());
        }

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }
}
