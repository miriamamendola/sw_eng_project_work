package test.controller;

import org.junit.Before;
import org.junit.Test;
import project_work.controller.Invoker;
import project_work.controller.LineTool;
import project_work.model.DrawableLine;
import project_work.view.CanvasView;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class LineToolTest {

    private LineTool lineTool;
    private CanvasView canvas;
    private Invoker invoker;

    @Before
    public void setUp() {
        canvas = new CanvasView();
        invoker = new Invoker();
        lineTool = new LineTool(canvas, invoker);

    }

    @Test
    public void testMousePressed() {
        Point2D clickPoint = new Point2D.Double(10, 10);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        lineTool.mousePressed(e);
        DrawableLine line = (DrawableLine) canvas.getDummyDrawable();
        assertEquals(line.getP1(), clickPoint);
    }

    @Test
    public void testMouseDragged() {
        Point2D clickPoint = new Point2D.Double(10, 10);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        lineTool.mousePressed(e);

        clickPoint = new Point2D.Double(20, 20);
        e = new MouseEvent(canvas, MouseEvent.MOUSE_DRAGGED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        lineTool.mouseDragged(e);

        DrawableLine line = (DrawableLine) canvas.getDummyDrawable();
        assertEquals(line.getP2(), clickPoint);
    }

    @Test
    public void testMouseReleased() {
        Point2D clickPoint = new Point2D.Double(20, 20);

        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        lineTool.mousePressed(e);

        clickPoint = new Point2D.Double(40, 40);
        e = new MouseEvent(canvas, MouseEvent.MOUSE_DRAGGED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        lineTool.mouseDragged(e);

        DrawableLine line = (DrawableLine) canvas.getDummyDrawable();

        e = new MouseEvent(canvas, MouseEvent.MOUSE_RELEASED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        lineTool.mouseReleased(e);

        assertNull(canvas.getDummyDrawable());

        assertTrue(canvas.getDrawing().containsDrawable(line));
    }

}