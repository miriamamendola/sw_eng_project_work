package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;

/**
 * Defines the Cut Command, which will first store the selected Drawable in the clipboard and then will ask the
 * receiver (canvas) to remove it in order to avoid duplicates, being this a CUT command.
 */
public class CutCommand implements Command {
    private CanvasView canvas;

    public CutCommand(CanvasView canvas) {
        this.canvas = canvas;
    }

    /**
     * Allows to ask for the execution of the Cut Command: the selected Drawable is wrapped in a specific class
     * and then stored in the clipboard. Subsequently, the canvas is asked to REMOVE the Drawable from the drawing.
     */
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