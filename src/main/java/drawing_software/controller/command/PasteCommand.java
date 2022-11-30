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
            String s = copiedShape.getClass().toString().substring(29);
            copiedShape.setLocation(point);
            canvas.clearSelectedDrawable();
            canvas.getDrawing().addDrawable(copiedShape);
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
