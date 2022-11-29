package drawing_software.controller.command;

import drawing_software.Context;
import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;

public class CopyCommand implements Command{

    private final CanvasView canvas;
    private Drawable ss;


    public CopyCommand(CanvasView canvas, Drawable ss) {
        this.canvas = canvas;
        this.ss = ss;
    }

    @Override
    public void execute() {
        /*System.out.println("copiaa");
        Context.getInstance().setSaved(false);
        canvas.setCopiedShape(ss);*/
        Drawable selectedShape = canvas.getSelectionGrid().getSelectedShape();         //now i have selected shape to copy
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        TrasferableWrapper tr = new TrasferableWrapper(selectedShape);
        clipboard.setContents(tr.getSelectedShape(),canvas);
    }
}
