package project_work.view;

import project_work.Context;
import project_work.controller.SelectionTool;
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



    private Drawable selectedDrawable;

    public CanvasView() {
        this.drawing = Context.getInstance().getCurrentDrawing();
        currentTool = new SelectionTool(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {currentTool.mouseClicked(e); }
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
    public Drawable getSelectedDrawable() {
        return selectedDrawable;
    }

    public void setSelectedDrawable(Drawable selectedDrawable) {
        this.selectedDrawable = selectedDrawable;
    }

    public void clearSelectedDrawable(){
        this.selectedDrawable = null;
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

        if(selectedDrawable != null){
            System.out.println("AA");
            selectedDrawable.draw((Graphics2D) g);
        }
    }
}
