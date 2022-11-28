package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.model.Drawable;
import drawing_software.model.SelectionGrid;
import drawing_software.view.CanvasView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.logging.Logger;


public class CopyTool implements Tool {
    private final CanvasView canvasView;
    private final Invoker invoker;
    private Shape copiedShape;

    public Shape getCopiedShape() {
        return copiedShape;
    }

    public void setCopiedShape(Shape copiedShape) {
        this.copiedShape = copiedShape;
    }



    public CopyTool(CanvasView canvasView, Invoker invoker) {
        this.canvasView = canvasView;
        this.invoker= invoker;
        this.copiedShape = null;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        Point2D point = mouseEvent.getPoint();
        boolean found = false;
        Iterator<Drawable> itr = canvasView.getDrawing().descendingIterator();
        while (itr.hasNext()) {
            Shape s = (Shape) itr.next();
            if (s.contains(point)) {
                canvasView.setSelectionGrid(new SelectionGrid(s));
                canvasView.repaint();
                this.setCopiedShape(s);
                break;
            }
        }
    }
}
