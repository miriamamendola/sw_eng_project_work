package drawing_software.controller.tool.handler;

import drawing_software.controller.tool.MouseRequest;
import drawing_software.model.Drawable;
import drawing_software.model.SelectionGrid;
import drawing_software.model.Shape;
import drawing_software.view.Canvas;

import java.awt.geom.Point2D;
import java.util.Iterator;

public class SelectionHandler extends Handler {

    public SelectionHandler(Canvas canvas) {
        super(canvas);
    }


    @Override
    public void handlePressed(MouseRequest request) {

        Point2D point = request.getMouseEvent().getPoint();
        boolean found = false;
        Iterator<Drawable> itr = canvas.getDrawing().descendingIterator();

        while (itr.hasNext()) {
            Shape s = (Shape) itr.next();

            if (canvas.getSelectionGrid() != null) {
                // salviamo la vecchia dimensione della shape per il resize command
                request.setOldShapeSize(s.getBounds().getSize());
                // salvo la vecchia posizione della shape per move e resize
                request.setOldShapeLocation(request.getSelectedShape().getBounds().getLocation());
                request.setPrevMouse(point);
                // controllo che la shape selezionata è la shape corrente...
                // e ho cliccato su un vertice
                if (canvas.getSelectionGrid().getSelectedShape().equals(s) && canvas.getSelectionGrid().isVertexClicked(point)) {               //se ho selezionato un vertice
                    setNext(new ResizeHandler(canvas));
                    found = true;
                    break;
                }
            }

            //se il punto è contenuto in una shape
            if (s.contains(point)) {
                // la shape selezionata è selected shape
                request.setSelectedShape(s);

                // creiamo la selection grid
                SelectionGrid grid = new SelectionGrid(s);
                canvas.setSelectionGrid(grid);

                // imposta la ratio per fare la resize
                request.setRatio(grid.getWidth() / grid.getHeight());
                // serve alla move per determinare il vettore di traslazione
                request.setPrevMouse(point);
                canvas.repaint();
                found = true;
                break;
            }
        }

        // se non ho trovato niente
        if (!found) {
            canvas.clearSelectedDrawable();
            canvas.repaint();
            request.setSelectedShape(null);
            setNext(null);
        }

        if (nextHandler != null) {
            nextHandler.handlePressed(request);
        }
    }

    @Override
    public void handleDragged(MouseRequest request) {

        if (canvas.getSelectionGrid().getSelectedVertex() != -1) {                  //if a vertex is selected
            setNext(new ResizeHandler(canvas));
        } else {
            setNext(new MoveHandler(canvas));
        }

        if (nextHandler != null) {
            nextHandler.handleDragged(request);
        }
    }

}
