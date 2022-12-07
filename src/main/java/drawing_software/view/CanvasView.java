package drawing_software.view;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.SelectionTool;
import drawing_software.controller.tool.Tool;
import drawing_software.model.Drawable;
import drawing_software.model.Drawing;
import drawing_software.model.SelectionGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

public class CanvasView extends JPanel implements ClipboardOwner,MouseWheelListener  {

    private Drawing drawing;
    private Drawable dummyDrawable;
    private Tool currentTool;

    private Color currentFillColor;

    private Color currentStrokeColor = Color.black;

    private SelectionGrid selectionGrid;

    private Drawable copiedShape;

    private double scaleFactor = 1;
    private Point2D scalePoint = new Point(0,0);
    public CanvasView(Invoker invoker) {
        this.drawing = new Drawing();
        currentTool = new SelectionTool(this, invoker);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    currentTool.mouseLeftClicked(e);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    currentTool.mouseRightClicked(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON1)
                    currentTool.mouseLeftPressed(e);
                else if (e.getButton() == MouseEvent.BUTTON3)
                    currentTool.mouseRightPressed(e);
                ;
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
        this.addMouseWheelListener(this);

    }



    public Drawing getDrawing() {
        return drawing;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    public Drawable getDummyDrawable() {
        return dummyDrawable;
    }

    public void setDummyDrawable(Drawable drawable) {
        this.dummyDrawable = drawable;
    }

    public void clearDummyDrawable() {
        this.dummyDrawable = null;
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    public SelectionGrid getSelectionGrid() {
        return selectionGrid;
    }

    public void setSelectionGrid(SelectionGrid selectionGrid) {
        this.selectionGrid = selectionGrid;
    }

    public void clearSelectedDrawable() {
        this.selectionGrid = null;
    }

    public void clearCopiedShape() {
        this.copiedShape = null;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public Color getCurrentFillColor() {
        return currentFillColor;
    }

    public void setCurrentFillColor(Color currentFillColor) {
        this.currentFillColor = currentFillColor;
    }

    public Color getCurrentStrokeColor() {
        return currentStrokeColor;
    }

    public void setCurrentStrokeColor(Color currentStrokeColor) {
        this.currentStrokeColor = currentStrokeColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d =(Graphics2D) g ;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setStroke(new BasicStroke(1));
        g2d.translate(scalePoint.getX(), scalePoint.getY());
        g2d.scale(scaleFactor, scaleFactor);
        g2d.translate(-scalePoint.getX(),- scalePoint.getY());
        for (Drawable d : this.drawing) {
            d.draw(g2d);
        }

        if (dummyDrawable != null) {
            dummyDrawable.draw(g2d);
        }

        if (selectionGrid != null) {
            selectionGrid.draw((Graphics2D) g);
        }
    }

    public Drawable getCopiedShape() {
        return copiedShape;
    }

    public void setCopiedShape(Drawable copiedShape) {
        this.copiedShape = copiedShape;
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("ClipboardTest: Lost ownership");
    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.scalePoint = e.getPoint();
        System.out.println(scalePoint);
        this.scaleFactor += (-e.getWheelRotation() * 0.2);
        this.repaint();
    }
}
