package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

public class CopyCommand implements Command ,ClipboardOwner{

    private final CanvasView canvas;


    public CopyCommand(CanvasView canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        Drawable selectedShape = canvas.getSelectionGrid().getSelectedShape();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        TrasferableWrapper tr = new TrasferableWrapper(selectedShape);
        clipboard.setContents(tr, canvas);
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("ClipboardTest: Lost ownership");
    }
}
