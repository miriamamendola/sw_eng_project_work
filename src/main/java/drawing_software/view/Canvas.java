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
import java.util.logging.Logger;

public class Canvas extends JPanel implements ClipboardOwner {

    private Drawing drawing;

    private Drawable dummyDrawable;

    private Tool currentTool;

    private Color currentFillColor;

    private Color currentStrokeColor = Color.black;

    private SelectionGrid selectionGrid;

    private Drawable copiedShape;

    private boolean fixedResize;
    public Canvas(Invoker invoker) {
        this.drawing = new Drawing();
        currentTool = new SelectionTool(this, invoker);
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
        Tool oldTool = this.currentTool;
        this.currentTool = currentTool;
        firePropertyChange("CURRENT_TOOL", oldTool, currentTool);
    }

    public SelectionGrid getSelectionGrid() {
        return selectionGrid;
    }

    public void setSelectionGrid(SelectionGrid selectionGrid) {
        firePropertyChange("SELECTION", this.selectionGrid, selectionGrid);
        this.selectionGrid = selectionGrid;
    }

    public void clearSelectedDrawable() {
        firePropertyChange("SELECTION", this.selectionGrid, null);
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
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setStroke(new BasicStroke(1));

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
        Logger.getLogger("root").info("ClipboardTest: Lost ownership");
    }

    public boolean isFixedResize() {
        return fixedResize;
    }

    public void setFixedResize(boolean fixedResize) {
        this.fixedResize = fixedResize;
    }
}
