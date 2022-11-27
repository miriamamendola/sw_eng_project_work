package src.view.toolbar;

import src.view.CanvasView;

import javax.swing.*;

public abstract class ToolbarItemFactory {

    protected final static int ICON_SIZE = 32;

    protected final CanvasView canvasView;

    public ToolbarItemFactory(CanvasView canvasView) {
        this.canvasView = canvasView;
    }

    public abstract JButton itemCreate();
}
