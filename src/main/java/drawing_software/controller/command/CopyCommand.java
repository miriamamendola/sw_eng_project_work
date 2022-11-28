package drawing_software.controller.command;

import drawing_software.model.Drawable;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

public class CopyCommand implements Command{
    private final Shape selectedShape ;

    public CopyCommand(Shape s) {
        this.selectedShape = s;
    }

    @Override
    public void execute() {
        System.out.println("copiaa");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents((Transferable) selectedShape, (ClipboardOwner) this);
    }
}
