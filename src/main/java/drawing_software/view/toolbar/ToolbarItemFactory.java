package drawing_software.view.toolbar;

import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import javax.swing.*;

/**
 * The concrete classes of this class are factory classes of the JButtons
 * placed inside the toolbar.
 */
public abstract class ToolbarItemFactory {

    protected final static int ICON_SIZE = 32;

    protected final CanvasView canvasView;
    protected final Invoker invoker;

    public ToolbarItemFactory(CanvasView canvasView, Invoker invoker) {
        this.canvasView = canvasView;
        this.invoker = invoker;
    }

    public abstract JButton itemCreate();
}