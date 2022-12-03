package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

/**
 * Implements a ConcreteCommand class; in this case, the Command is the Copy command.
 */
public class CopyCommand implements Command ,ClipboardOwner{

    private final CanvasView canvas;

    /**
     * @param canvas is the reference to the receiver which will perform the required action.
     */

    public CopyCommand(CanvasView canvas) {
        this.canvas = canvas;
    }

    /**
     * First,it takes the selected figure to copy, and then it creates a clipboard where save the figure.
     * The operation is completed by adding the shapes in the canvas and repaint the canvas in order to can paste easily .
     *
     */
    @Override
    public void execute() {
        Drawable selectedShape = canvas.getSelectionGrid().getSelectedShape();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        TrasferableWrapper tr = new TrasferableWrapper(selectedShape);
        clipboard.setContents(tr, canvas);
    }

    /**
     * This method is necessary for use a clipboard
     */
    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("ClipboardTest: Lost ownership");
    }
}
