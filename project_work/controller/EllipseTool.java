package project_work.controller;

import project_work.Context;
import project_work.model.DrawableEllipse;
import project_work.model.DrawableRectangle;
import project_work.view.CanvasView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EllipseTool implements Tool, MouseListener, MouseMotionListener {

    private DrawableEllipse ellipse;

    private final CanvasView canvas;

    public EllipseTool(CanvasView canvas){
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        Context.getInstance().getCurrentDrawing().addDrawable(ellipse);
    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        double width = mouseEvent.getX() - ellipse.getX();
        double height = mouseEvent.getY() - ellipse.getY();
        ellipse.setSize(width, height);
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
        ellipse = new DrawableEllipse(mouseEvent.getX(), mouseEvent.getY());
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
