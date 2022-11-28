package drawing_software.view.toolbar;

import drawing_software.view.CanvasView;

import javax.swing.*;

/**
 * The concrete classes of this class are factory classes of the JButtons
 * placed inside the toolbar.
 */
public abstract class ToolbarItemFactory {

    protected final static int ICON_SIZE = 32;

    protected final CanvasView canvasView;

    public ToolbarItemFactory(CanvasView canvasView) {
        this.canvasView = canvasView;
    }

    public abstract JButton itemCreate();
}
