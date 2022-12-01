package drawing_software.controller.tool;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.PasteCommand;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static java.lang.Thread.sleep;


public class PasteTool extends JPanel implements Tool {
    private final CanvasView canvas;
    private final Invoker invoker;
    private boolean c = false;


    public PasteTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
    }

    @Override
    public void mouseLeftClicked(MouseEvent mouseEvent) {
        c = true;
        Point2D p = mouseEvent.getPoint();
        invoker.executeCommand(new PasteCommand(canvas, p));
        c = false;
    }

    public void mouseDragged(MouseEvent mouseEvent){
        Point2D p = mouseEvent.getPoint();
        invoker.executeCommand(new PasteCommand(canvas, p));
        try {
            sleep(57);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

