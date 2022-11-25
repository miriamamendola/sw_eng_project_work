package project_work.view;

import project_work.Context;
import project_work.controller.DefaultTool;
import project_work.controller.Tool;
import project_work.model.Drawable;
import project_work.model.Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CanvasView extends JPanel {

    private Drawing drawing;
    private Drawable dummyDrawable;
    private Tool currentTool;



    private Shape selectedShape;

    public CanvasView() {
        this.drawing = Context.getInstance().getCurrentDrawing();
        currentTool = new DefaultTool(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {currentTool.mouseClicked(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                currentTool.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentTool.mouseReleased(e);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentTool.mouseDragged(e);
            }
        });
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    public void setDummyDrawable(Drawable drawable) {
        this.dummyDrawable = drawable;
    }

    public void clearDummyDrawable(){
        this.dummyDrawable = null;
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }
    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
    }
    public Tool getCurrentTool() {
        return currentTool;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0,0, this.getWidth(), this.getHeight());

        for (Drawable d : this.drawing) {
            d.draw((Graphics2D) g);
        }

        if(dummyDrawable != null){
            dummyDrawable.draw((Graphics2D) g);
        }
    }

}
