package project_work.controller;

import project_work.model.DrawableLine;
import project_work.view.CanvasView;

import java.awt.event.MouseEvent;

public class LineTool implements Tool {

    private DrawableLine line;

    private final CanvasView canvas;

    public LineTool(CanvasView canvas){
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        line = new DrawableLine(mouseEvent.getPoint(), mouseEvent.getPoint());
        canvas.setDummyDrawable(line);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        line.setLine(line.getP1(), mouseEvent.getPoint());
        canvas.repaint();
    }


    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(!line.getP1().equals(line.getP2())){
            canvas.getDrawing().addDrawable(line);
        }

    }

}
