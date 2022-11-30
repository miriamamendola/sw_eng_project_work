package drawing_software.controller.command;

import drawing_software.Context;
import drawing_software.model.Drawable;
import drawing_software.model.Shape;
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
        System.out.println("execute");
        Shape selectedShape = (Shape) canvas.getSelectionGrid().getSelectedShape();         //now i have selected shape to copy
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        TrasferableWrapper tr = new TrasferableWrapper(selectedShape);
        try {
            TrasferableWrapper t2 = (TrasferableWrapper) tr.getSelectedShape();
            clipboard.setContents(t2,canvas);

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("ClipboardTest: Lost ownership");
    }
}
