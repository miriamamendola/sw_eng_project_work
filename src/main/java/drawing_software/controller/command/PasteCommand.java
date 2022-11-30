package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.model.DrawableEllipse;
import drawing_software.model.DrawableLine;
import drawing_software.model.DrawableRectangle;
import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.lang.reflect.Type;

public class PasteCommand implements Command {
    private CanvasView canvas;
    private Point2D point;

    public PasteCommand(CanvasView canvas, Point2D point) {
        this.canvas = canvas;
        this.point = point;
    }
    @Override
    public void execute() {
        System.out.println("Sei nel command di paste");
        DataFlavor dataFlavor = null;
        try {
            dataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType +
                    ";class=\"" + Drawable.class.getName() + "\"");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable tr = clipboard.getContents(this);
            Shape copiedShape = (Shape) tr.getTransferData(dataFlavor);
            canvas.getDrawing().addDrawable((Drawable)copiedShape);
            copiedShape.setLocation(point);
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
