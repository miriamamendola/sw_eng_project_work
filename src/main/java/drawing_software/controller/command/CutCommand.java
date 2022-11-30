package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;

public class CutCommand implements Command {
    private CanvasView canvas;

    public CutCommand(CanvasView canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        Drawable selectedShape = canvas.getSelectionGrid().getSelectedShape();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        TrasferableWrapper tr = new TrasferableWrapper(selectedShape);
        canvas.getDrawing().removeDrawable(selectedShape);
        clipboard.setContents(tr, canvas);
        canvas.clearSelectedDrawable();
        canvas.repaint();
    }


}