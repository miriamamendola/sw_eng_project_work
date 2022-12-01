package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.model.Drawable;
import drawing_software.model.SelectionGrid;
import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

public class SelectionTool implements Tool {
    private final CanvasView canvas;
    private Invoker invoker;
    public SelectionTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
    }

    @Override
    public void mouseLeftClicked(MouseEvent mouseEvent) {

        Point2D point = mouseEvent.getPoint();
        boolean found = false;
        Iterator<Drawable> itr = canvas.getDrawing().descendingIterator();
        while (itr.hasNext()) {
            Shape s = (Shape) itr.next();
            if (s.contains(point)) {
                SelectionGrid grid = new SelectionGrid(s);
                canvas.setSelectionGrid(grid);
                if (grid.getVertex().contains(point)) {
                    System.out.println(grid.getVertex().getSelectedVertex());
                }
                canvas.repaint();
                found = true;
                break;
            }
        }
        if (!found) {
            canvas.clearSelectedDrawable();
            canvas.repaint();
        }

    }

    @Override
    public void mouseRightClicked(MouseEvent mouseEvent) {
        JPopupMenu popupMenu = new JPopupMenu();


        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        popupMenu.add(copyMenuItem);
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        popupMenu.add(pasteMenuItem);
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteShape();
            }
        });
        popupMenu.add(deleteMenuItem);
        popupMenu.show(canvas, mouseEvent.getX(), mouseEvent.getY());
    }


    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("a");
        if (KeyEvent.VK_DELETE == keyEvent.getKeyChar() || KeyEvent.VK_DELETE == keyEvent.getKeyCode()) {
            deleteShape();
        }

    }

    public void deleteShape() {
        canvas.getDrawing().removeDrawable((Shape) canvas.getSelectionGrid().getSelectedShape());
        canvas.clearSelectedDrawable();
        canvas.repaint();
    }
}
