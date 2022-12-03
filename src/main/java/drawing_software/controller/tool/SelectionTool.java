package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.model.Drawable;
import drawing_software.model.SelectionGrid;
import drawing_software.model.Shape;
import drawing_software.model.Vertex;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class SelectionTool implements Tool {
    private final CanvasView canvas;
    private Invoker invoker;

    private Point2D startingPoint;

    public SelectionTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
        startingPoint = new Point2D.Double(0, 0);
    }

    @Override
    public void mouseLeftClicked(MouseEvent mouseEvent) {

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
    public void mouseLeftPressed(MouseEvent mouseEvent) {
        Point2D point = mouseEvent.getPoint();
        boolean found = false;
        Iterator<Drawable> itr = canvas.getDrawing().descendingIterator();
        if (canvas.getSelectionGrid() == null) {
            while (itr.hasNext()) {
                Shape s = (Shape) itr.next();
                if (s.contains(point)) {
                    SelectionGrid grid = new SelectionGrid(s);
                    canvas.setSelectionGrid(grid);
                    canvas.repaint();
                    found = true;
                    break;
                }
            }
        } else {
            while (itr.hasNext()) {
                Shape s = (Shape) itr.next();
                if (canvas.getSelectionGrid().getSelectedShape().equals(s) && canvas.getSelectionGrid().isVertexClicked(point)) {
                    SelectionGrid grid = new SelectionGrid(s);
                    canvas.setSelectionGrid(grid);
                    if (canvas.getSelectionGrid().isVertexClicked(point)) {
                        int v = canvas.getSelectionGrid().getSelectedVertex();
                        if (v != -1) {

                            if (v == Vertex.UPLEFT) {
                                startingPoint = new Point2D.Double(canvas.getSelectionGrid().getX() + canvas.getSelectionGrid().getWidth(), canvas.getSelectionGrid().getY() + canvas.getSelectionGrid().getHeight());
                            } else if (v == Vertex.UPRIGHT) {
                                startingPoint = new Point2D.Double(canvas.getSelectionGrid().getX(), canvas.getSelectionGrid().getY() + canvas.getSelectionGrid().getHeight());
                            } else if (v == Vertex.BOTTOMLEFT) {
                                startingPoint = new Point2D.Double(canvas.getSelectionGrid().getX() + canvas.getSelectionGrid().getWidth(), canvas.getSelectionGrid().getY());
                            } else if (v == Vertex.BOTTOMRIGHT) {
                                startingPoint = new Point2D.Double(canvas.getSelectionGrid().getX(), canvas.getSelectionGrid().getY());
                            }
                        }
                    }
                    canvas.repaint();
                    found = true;
                    break;
                }

            }
        }
        if (!found) {
            canvas.clearSelectedDrawable();
            canvas.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (canvas.getSelectionGrid() != null) {
            if (canvas.getSelectionGrid().getSelectedVertex() != -1) {
                double x = min(startingPoint.getX(), mouseEvent.getX());
                double y = min(startingPoint.getY(), mouseEvent.getY());
                double width = abs(startingPoint.getX() - mouseEvent.getX());
                double height = abs(startingPoint.getY() - mouseEvent.getY());

                Shape shape = canvas.getSelectionGrid().getSelectedShape();
                shape.setFrame(new Point2D.Double(x, y), new Dimension((int) width, (int) height));
                canvas.getSelectionGrid().setFrame(new Point2D.Double(x, y), new Dimension((int) width, (int) height));
                canvas.repaint();

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (canvas.getSelectionGrid() != null) {
            canvas.getSelectionGrid().clearSelectedVertex();
        }

    }


    @Override
    public void keyPressed(KeyEvent keyEvent) {
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
