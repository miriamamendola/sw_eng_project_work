package drawing_software.view.colors;

import drawing_software.view.CanvasView;

import javax.swing.*;

public abstract class ColorPanelFactory extends JPanel {
    protected final CanvasView canvas;
    protected ColorButton button;

    protected ColorPanelFactory(CanvasView canvas) {
        this.canvas = canvas;
    }

    protected abstract JPanel createPanel();
}
