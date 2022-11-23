package project_work.controller;

import project_work.Context;
import project_work.model.DrawableEllipse;
import project_work.model.DrawableLine;
import project_work.view.CanvasView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class LineTool implements Tool, MouseListener, MouseMotionListener {

    private DrawableLine line;

    private final CanvasView canvas;

    public LineTool(CanvasView canvas){
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        Context.getInstance().getCurrentDrawing().addDrawable(line);
    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        line.setLine(line.getP1(), mouseEvent.getPoint());
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        line = new DrawableLine(mouseEvent.getPoint(), mouseEvent.getPoint());
        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
