package test.controller;

import org.junit.Before;
import org.junit.Test;
import project_work.controller.tool.SelectionTool;
import project_work.model.DrawableLine;
import project_work.view.CanvasView;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class SelectionToolTest {
    private CanvasView canvas;
    private SelectionTool selectionTool;

    @Before
    public void setUp() throws Exception {
        canvas = new CanvasView();
        selectionTool = new SelectionTool(canvas);
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