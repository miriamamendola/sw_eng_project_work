package src.controller.tool;

import src.controller.command.Invoker;
import src.controller.command.ShapeCommand;
import src.model.DrawableLine;
import src.view.CanvasView;

import java.awt.event.MouseEvent;

public class LineTool implements Tool {

    private DrawableLine line;

    private final CanvasView canvas;

    private final Invoker invoker;

    public LineTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
        canvas.clearSelectedDrawable();
        canvas.repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        line = new DrawableLine(canvas.getCurrentStrokeColor(), mouseEvent.getPoint(), mouseEvent.getPoint());
        canvas.setDummyDrawable(line);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        line.setLine(line.getP1(), mouseEvent.getPoint());
        canvas.repaint();
    }


    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (!line.getP1().equals(line.getP2())) {
            invoker.executeCommand(new ShapeCommand(canvas, line));
        }
        canvas.clearDummyDrawable();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

}
