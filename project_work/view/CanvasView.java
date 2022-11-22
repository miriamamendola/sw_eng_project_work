package project_work.view;

import project_work.Context;
import project_work.model.Drawable;
import project_work.model.Drawing;

import javax.swing.*;
import java.awt.*;

public class CanvasView extends JPanel {

    private Drawing drawing;

    public CanvasView() {
        this.drawing = Context.getInstance().getCurrentDrawing();
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
