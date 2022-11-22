package view;

import model.Drawable;
import model.Drawing;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class CanvasView extends JPanel {

    private Drawing drawing;

    public CanvasView(Drawing drawing) {
        this.drawing = drawing;
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0,0, this.getWidth(), this.getHeight());

        for (Drawable d : this.drawing) {
            d.draw((Graphics2D) g);
        }
    }
}
