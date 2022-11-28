package drawing_software.controller.command;

import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

public class CopyCommand implements Command{

    private final CanvasView canvas;


    public CopyCommand( CanvasView canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        System.out.println("copiaa");

        Shape selectedShape = canvas.getSelectionGrid().getSelectedShape();         //now i have selected shape to copy
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents((Transferable) selectedShape, (ClipboardOwner) this);
    }
}
