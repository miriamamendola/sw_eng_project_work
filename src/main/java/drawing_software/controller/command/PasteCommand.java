package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Point2D;
import java.io.IOException;

public class PasteCommand implements Command {
    private CanvasView canvas;
    private Point2D point;

    public PasteCommand(CanvasView canvas, Point2D point) {
        this.canvas = canvas;
        this.point = point;
    }
    @Override
    public void execute() {
        try {
            DataFlavor dataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=\"" + Drawable.class.getName() + "\"");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable tr = clipboard.getContents(this);
            Shape copiedShape = (Shape) tr.getTransferData(dataFlavor);
            Shape copiedShape2 = copiedShape.clone();
            System.out.println("wdfqwefwefg");
            canvas.getDrawing().addDrawable(copiedShape2);
            copiedShape2.setLocation(point);
            canvas.clearSelectedDrawable();
            canvas.repaint();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        }

    }


}
