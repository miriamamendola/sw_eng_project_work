package drawing_software.controller.command;

import drawing_software.Context;
import drawing_software.model.Drawable;
import drawing_software.model.DrawableLine;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Iterator;

public class PasteCommand implements Command {
    private CanvasView canvas;

    public PasteCommand(CanvasView canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        System.out.println("Sei nel command di paste");

        /*if (copiedShape == null) {
            Context.getInstance().setSaved(false);
            System.out.println("Nessuna figura precedentemente selezionata");
        }
        System.out.println("Aggiungo");
        System.out.println(copiedShape);
        System.out.println("al drawing");
        canvas.getDrawing().addDrawable(copiedShape);
        Iterator<Drawable> itr = canvas.getDrawing().iterator();
        while (itr.hasNext()) {
            Shape s = (Shape) itr.next();
            System.out.println(s.getBounds());
        }*/

        //creare costruttore vuoto all'inizio
        try {
            DataFlavor dataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType +
                            ";class=\"" + Drawable.class.getName() + "\"");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable tr = clipboard.getContents(this);
            System.out.println(tr.getTransferData(dataFlavor));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        }


    }
}
