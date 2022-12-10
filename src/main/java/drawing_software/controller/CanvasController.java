package drawing_software.controller;

import drawing_software.view.Canvas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CanvasController implements MouseListener, MouseMotionListener {
    private Canvas canvas;

    public CanvasController(Canvas canvas) {
        this.canvas = canvas;
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(canvas.getCurrentTool());
        if (e.getButton() == MouseEvent.BUTTON1) {
            canvas.getCurrentTool().mouseLeftClicked(e);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            canvas.getCurrentTool().mouseRightClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            canvas.getCurrentTool().mouseLeftPressed(e);
        else if (e.getButton() == MouseEvent.BUTTON3)
            canvas.getCurrentTool().mouseRightPressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvas.getCurrentTool().mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        canvas.getCurrentTool().mouseDragged(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
