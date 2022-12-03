package controller;

import drawing_software.controller.command.CopyCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.PasteCommand;
import drawing_software.controller.tool.SelectionTool;
import drawing_software.model.Drawable;
import drawing_software.model.DrawableRectangle;
import drawing_software.model.SelectionGrid;
import drawing_software.view.CanvasView;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class PasteCommandTest {
    private CanvasView canvas;
    private Invoker invoker;
    private DataFlavor df;
    private PasteCommand pc;
    private DrawableRectangle dr;
    private SelectionGrid sg;
    private CopyCommand cc;
    private SelectionTool selectionTool;

    @Before
    public void setUp() throws ClassNotFoundException {
        invoker = new Invoker();
        canvas = new CanvasView(invoker);
        pc = new PasteCommand(canvas, new Point2D.Double(42, 69));
        df = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=\"" + Drawable.class.getName() + "\"");
        dr = new DrawableRectangle(Color.white, Color.cyan, 10, 10);
        dr.setFrame(10, 10, 60, 60);
        sg = new SelectionGrid(dr);
        cc = new CopyCommand(canvas);
        selectionTool = new SelectionTool(canvas, invoker);
    }

    @Test
    public void textExecute() {
        canvas.setSelectionGrid(sg);
        canvas.getSelectionGrid().setSelectedShape(dr);
        cc.execute();
        pc.execute();
        Point2D clickPoint = new Point2D.Double(82, 88);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        selectionTool.mouseLeftClicked(e);
        Drawable dr2 = canvas.getSelectionGrid().getSelectedShape();
        Drawable dr3 = canvas.getDrawing().getDrawable(0);
        assertEquals(dr3, dr2);
    }
}