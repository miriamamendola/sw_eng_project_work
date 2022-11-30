package drawing_software.controller.tool;

import drawing_software.controller.command.CopyCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import java.awt.*;


public class CopyTool implements Tool {
    private final CanvasView canvasView;
    private final Invoker invoker;

    public Shape copiedShape;

    public CopyTool(CanvasView canvasView, Invoker invoker) {
        this.canvasView = canvasView;
        this.invoker = invoker;
        System.out.println("Entrato in copyTool");
        invoker.executeCommand(new CopyCommand(canvasView));
    }

    /*@Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point2D point = mouseEvent.getPoint();
        boolean found = false;

        Iterator<Drawable> itr = canvasView.getDrawing().descendingIterator();
        while (itr.hasNext()) {
            Drawable d = itr.next();
            Shape s = (Shape) d;
            if (s.contains(point)) {
                canvasView.setSelectionGrid(new SelectionGrid(s));
                canvasView.repaint();
                invoker.executeCommand(new CopyCommand(canvasView, d));
                found = true;
                break;
            }

        }
        if (found) {
            System.out.println("La figura copiata è");
            System.out.println(canvasView.getCopiedShape());
        } else {
            canvasView.clearCopiedShape();
            canvasView.clearSelectedDrawable();
            System.out.println("Nessuna figura selezionata");
            canvasView.repaint();
        }

    }*/
}
