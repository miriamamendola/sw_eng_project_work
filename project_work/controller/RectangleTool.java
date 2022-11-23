package project_work.controller;

import project_work.Context;
import project_work.model.DrawableRectangle;
import project_work.view.CanvasView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RectangleTool implements Tool, MouseListener, MouseMotionListener {

    private DrawableRectangle rect;

    private final CanvasView canvas;

    public RectangleTool(CanvasView canvas){
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        Context.getInstance().getCurrentDrawing().addDrawable(rect);
    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        double width = mouseEvent.getX() - rect.getX();
        double height = mouseEvent.getY() - rect.getY();
        rect.setSize(width, height);
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
        rect = new DrawableRectangle(mouseEvent.getX(), mouseEvent.getY());
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
