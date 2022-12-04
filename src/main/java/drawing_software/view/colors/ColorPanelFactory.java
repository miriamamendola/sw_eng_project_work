package drawing_software.view.colors;

import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import javax.swing.*;

public abstract class ColorPanelFactory extends JPanel {

    protected final Invoker invoker;

    protected final CanvasView canvas;
    protected ColorButton button;

    protected ColorPanelFactory(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
    }

    protected abstract JPanel createPanel();

    public ColorButton getButton() {
        return button;
    }
}
