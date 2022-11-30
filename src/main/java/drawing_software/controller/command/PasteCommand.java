package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.model.DrawableEllipse;
import drawing_software.model.DrawableLine;
import drawing_software.model.DrawableRectangle;
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
            Drawable copiedShape = (Drawable) tr.getTransferData(dataFlavor);
            System.out.println(copiedShape);
            System.out.println(copiedShape.getClass().toString());
            Type c = copiedShape.getClass();

            if (c.equals(DrawableRectangle.class)) {
                DrawableRectangle dr = (DrawableRectangle) copiedShape;
                System.out.println("rettangolo");
                DrawableRectangle rec = new DrawableRectangle(dr.getFillColor(), dr.getStrokeColor(), point.getX(), point.getY());
                rec.setRect(point.getX(), point.getY(), dr.getBounds().getWidth(), dr.getBounds().getHeight());
                canvas.getDrawing().addDrawable(rec);
                canvas.repaint();
            } else if (c.equals(DrawableEllipse.class)) {
                DrawableEllipse dr = (DrawableEllipse) copiedShape;
                System.out.println("ellisse");
                DrawableEllipse ell = new DrawableEllipse(dr.getFillColor(), dr.getStrokeColor(), point.getX(), point.getY());
                ell.setFrame(point.getX(), point.getY(), dr.getBounds().getWidth(), dr.getBounds().getHeight());
                canvas.getDrawing().addDrawable(ell);
                canvas.repaint();
            } else {
                DrawableLine dr = (DrawableLine) copiedShape;
                System.out.println("line");

                DrawableLine lin = new DrawableLine(dr.getStrokeColor(), point, point);
                lin.setLine(point.getX(), point.getY(), dr.getBounds().getWidth(), dr.getBounds().getHeight());
                canvas.getDrawing().addDrawable(lin);
                canvas.repaint();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        }

    }
}
