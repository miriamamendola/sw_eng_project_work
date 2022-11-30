package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.MoveCommand;
import drawing_software.model.Drawable;
import drawing_software.model.SelectionGrid;
import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

public class SelectionTool implements Tool {
    private final CanvasView canvas;
    private final Invoker invoker;

    private Shape selectedShape;

    private Point2D oldShapeLocation;

    private Point2D prevMouse;

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
            Shape s = (Shape) itr.next();
            if (s.contains(point)) {
                this.selectedShape = s;
                canvas.setSelectionGrid(new SelectionGrid(selectedShape));
                prevMouse = mouseEvent.getPoint();
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
    public void mousePressed(MouseEvent mouseEvent) {
        if(selectedShape==null) return;
        oldShapeLocation = selectedShape.getBounds().getLocation();
        prevMouse = mouseEvent.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if(selectedShape==null) return;
        int delta_x = (int) (mouseEvent.getX() - prevMouse.getX());
        int delta_y = (int) (mouseEvent.getY() - prevMouse.getY());

        selectedShape.setLocation(selectedShape.getBounds().getX() + delta_x, selectedShape.getBounds().getY() + delta_y);

        prevMouse = mouseEvent.getPoint();
        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(selectedShape==null) return;
        invoker.executeCommand(new MoveCommand(canvas, oldShapeLocation));
    }
}
