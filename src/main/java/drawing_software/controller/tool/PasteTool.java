package drawing_software.controller.tool;


import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.PasteCommand;
import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;


public class PasteTool implements Tool {
    private final CanvasView canvas;
    private final Invoker invoker;


    public PasteTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
        canvas.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        /*System.out.println("incolla");
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        Drawable cp = canvas.getCopiedShape();
        invoker.executeCommand(new PasteCommand(canvas, canvas.getCopiedShape()));
        canvas.repaint();*/
        Point2D p = mouseEvent.getPoint();
        invoker.executeCommand(new PasteCommand(canvas,p));
    }
}

