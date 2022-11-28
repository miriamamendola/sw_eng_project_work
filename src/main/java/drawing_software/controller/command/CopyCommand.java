package drawing_software.controller.command;

import drawing_software.Context;
import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

public class CopyCommand implements Command{

    private final CanvasView canvas;
    private Drawable ss;


    public CopyCommand(CanvasView canvas, Drawable ss) {
        this.canvas = canvas;
        this.ss = ss;
    }

    @Override
    public void execute() {
        System.out.println("copiaa");
        Context.getInstance().setSaved(false);
        canvas.setCopiedShape(ss);
        //Shape selectedShape = canvas.getSelectionGrid().getSelectedShape();         //now i have selected shape to copy
        //Toolkit.getDefaultToolkit().getSystemClipboard().setContents((Transferable) selectedShape, (ClipboardOwner) this);
    }
}
