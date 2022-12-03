package drawing_software.view.menu;

import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import javax.swing.*;

/**
 * The concrete classes of this interface are factory classes of menu items
 * placed inside the JMenuBar.
 */
public abstract class MenuItemFactory {
    protected final CanvasView canvas;
    protected final Invoker invoker;

    protected MenuItemFactory(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
    }

    public abstract JMenuItem createMenuItem();

}
