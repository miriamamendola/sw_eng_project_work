package controller;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.SelectionTool;
import drawing_software.model.DrawableLine;
import drawing_software.view.CanvasView;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class SelectionToolTest {
    private CanvasView canvas;
    private SelectionTool selectionTool;

    private Invoker invoker;

    @Before
    public void setUp() throws Exception {
        invoker = new Invoker();
        canvas = new CanvasView(invoker);
        selectionTool = new SelectionTool(canvas, invoker);
    }
    @Test
    public void testMouseClickOnFigure() {
        Point2D start = new Point(0,0);
        Point2D end = new Point(0,20);
        DrawableLine lineToTest = new DrawableLine(start,end);       //create line to test
        canvas.getDrawing().addDrawable(lineToTest);
        Point2D clickPoint = new Point2D.Double(0, 10);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        selectionTool.mouseClicked(e);
        DrawableLine s =  (DrawableLine) canvas.getSelectionGrid().getSelectedShape();
        assertEquals(s,lineToTest);
    }
}