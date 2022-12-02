package controller;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.ShapeCommand;
import drawing_software.controller.command.TrasferableWrapper;
import drawing_software.controller.tool.SelectionTool;
import drawing_software.model.Drawable;
import drawing_software.model.DrawableLine;
import drawing_software.model.DrawableRectangle;
import drawing_software.model.Shape;
import drawing_software.view.CanvasView;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class CopyCommandTest {
    private CanvasView canvas;
    private Invoker invoker;
    private SelectionTool selectionTool;

    @Before
    public void setUp() {
        invoker=new Invoker();
        canvas = new CanvasView(invoker);
        selectionTool = new SelectionTool(canvas, invoker);


    }
    @Test
    public void testExecuteCopyLine() throws ClassNotFoundException {
        Point2D start = new Point(0,0);
        Point2D end = new Point(0,20);
        DrawableLine lineToTest = new DrawableLine(start,end);       //create line to test
        canvas.getDrawing().addDrawable(lineToTest);                 //add line into canvas
        Point2D clickPoint = new Point2D.Double(0, 10);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        selectionTool.mouseLeftClicked(e);
        DrawableLine s =  (DrawableLine) canvas.getSelectionGrid().getSelectedShape();      //take selected figure
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();             //get the clipboard
        TrasferableWrapper tr = new TrasferableWrapper(s);
        clipboard.setContents(tr, canvas);                                                  //add the figure to clipboard
        //now i take value from clipboard and i check if the value insert is equals to value taken
        DataFlavor dataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=\"" + Drawable.class.getName() + "\"");
        Transferable taken = clipboard.getContents(this);
        //drawing_software.model.Shape copiedShape = (Shape) tr.getTransferData(dataFlavor);
        DrawableLine copiedShape = (DrawableLine)tr.getTransferData(dataFlavor);
        assertEquals(s,copiedShape);
        assertEquals(s.getStrokeColor(),copiedShape.getStrokeColor());
    }



    @Test
    public void testExecuteCopyRectangle() throws ClassNotFoundException {
        Point2D start = new Point(0,0);
        Point2D end = new Point(0,20);
        DrawableRectangle rectangleToTest = new DrawableRectangle(start,end);       //create rectangle to test
        canvas.getDrawing().addDrawable(lineToTest);                 //add rectangle into canvas
        Point2D clickPoint = new Point2D.Double(0, 10);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        selectionTool.mouseLeftClicked(e);
        DrawableLine s =  (DrawableLine) canvas.getSelectionGrid().getSelectedShape();      //take selected figure
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();             //get the clipboard
        TrasferableWrapper tr = new TrasferableWrapper(s);
        clipboard.setContents(tr, canvas);                                                  //add the figure to clipboard
        //now i take value from clipboard and i check if the value insert is equals to value taken
        DataFlavor dataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=\"" + Drawable.class.getName() + "\"");
        Transferable taken = clipboard.getContents(this);
        //drawing_software.model.Shape copiedShape = (Shape) tr.getTransferData(dataFlavor);
        DrawableLine copiedShape = (DrawableLine)tr.getTransferData(dataFlavor);
        assertEquals(s,copiedShape);
        assertEquals(s.getStrokeColor(),copiedShape.getStrokeColor());
    }  @Test
    public void testExecuteCopyELlipse() throws ClassNotFoundException {
        Point2D start = new Point(0,0);
        Point2D end = new Point(0,20);
        DrawableLine lineToTest = new DrawableLine(start,end);       //create line to test
        canvas.getDrawing().addDrawable(lineToTest);                 //add line into canvas
        Point2D clickPoint = new Point2D.Double(0, 10);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        selectionTool.mouseLeftClicked(e);
        DrawableLine s =  (DrawableLine) canvas.getSelectionGrid().getSelectedShape();      //take selected figure
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();             //get the clipboard
        TrasferableWrapper tr = new TrasferableWrapper(s);
        clipboard.setContents(tr, canvas);                                                  //add the figure to clipboard
        //now i take value from clipboard and i check if the value insert is equals to value taken
        DataFlavor dataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=\"" + Drawable.class.getName() + "\"");
        Transferable taken = clipboard.getContents(this);
        drawing_software.model.Shape copiedShape = (Shape) tr.getTransferData(dataFlavor);
        assertEquals(s,copiedShape);
    }

}