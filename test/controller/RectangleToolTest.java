package test.controller;

import org.junit.Before;
import org.junit.Test;
import src.controller.command.Invoker;
import src.controller.tool.RectangleTool;
import src.model.DrawableRectangle;
import src.view.CanvasView;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class RectangleToolTest {

    private CanvasView canvas;
    private Invoker invoker;
    private RectangleTool rectangleTool;

    @Before
    public void setUp() throws Exception {
        canvas = new CanvasView();
        invoker = new Invoker();
        rectangleTool = new RectangleTool(canvas, invoker);
    }


    @Test
    public void testMouseDraggedTopLeft() {

        DrawableRectangle testRectangle = new DrawableRectangle(0,0);
        testRectangle.setRect(0,0,50,50);

        Point2D clickPoint = new Point2D.Double(0, 0);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mousePressed(e);

        clickPoint = new Point2D.Double(50, 50);
        e = new MouseEvent(canvas, MouseEvent.MOUSE_DRAGGED, 2, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseDragged(e);
        DrawableRectangle rectangle = (DrawableRectangle) canvas.getDummyDrawable();
        assertEquals(rectangle.getX(), testRectangle.getX(), 0);
        assertEquals(rectangle.getY(), testRectangle.getY(), 0);
        assertEquals(rectangle.getWidth(), testRectangle.getWidth(), 0);
        assertEquals(rectangle.getHeight(), testRectangle.getHeight(), 0);
    }

    @Test
    public void testMouseDraggedTopRight() {

        DrawableRectangle testRectangle = new DrawableRectangle(0,0);
        testRectangle.setRect(0,0,50,50);

        Point2D clickPoint = new Point2D.Double(50, 0);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mousePressed(e);

        clickPoint = new Point2D.Double(0, 50);
        e = new MouseEvent(canvas, MouseEvent.MOUSE_DRAGGED, 2, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseDragged(e);
        DrawableRectangle rectangle = (DrawableRectangle) canvas.getDummyDrawable();
        assertEquals(rectangle.getX(), testRectangle.getX(), 0);
        assertEquals(rectangle.getY(), testRectangle.getY(), 0);
        assertEquals(rectangle.getWidth(), testRectangle.getWidth(), 0);
        assertEquals(rectangle.getHeight(), testRectangle.getHeight(), 0);
    }

    @Test
    public void testMouseDraggedBottomLeft(){
        DrawableRectangle testRectangle = new DrawableRectangle(0,0);
        testRectangle.setRect(0,0,50,50);

        Point2D clickPoint = new Point2D.Double(0, 50);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mousePressed(e);

        clickPoint = new Point2D.Double(50, 0);
        e = new MouseEvent(canvas, MouseEvent.MOUSE_DRAGGED, 2, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseDragged(e);
        DrawableRectangle rectangle = (DrawableRectangle) canvas.getDummyDrawable();
        assertEquals(rectangle.getX(), testRectangle.getX(), 0);
        assertEquals(rectangle.getY(), testRectangle.getY(), 0);
        assertEquals(rectangle.getWidth(), testRectangle.getWidth(), 0);
        assertEquals(rectangle.getHeight(), testRectangle.getHeight(), 0);
    }

    @Test
    public void testMouseDraggedBottomRight(){
        DrawableRectangle testRectangle = new DrawableRectangle(0,0);
        testRectangle.setRect(0,0,50,50);

        Point2D clickPoint = new Point2D.Double(50, 50);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mousePressed(e);

        clickPoint = new Point2D.Double(0, 0);
        e = new MouseEvent(canvas, MouseEvent.MOUSE_DRAGGED, 2, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseDragged(e);
        DrawableRectangle rectangle = (DrawableRectangle) canvas.getDummyDrawable();
        assertEquals(rectangle.getX(), testRectangle.getX(), 0);
        assertEquals(rectangle.getY(), testRectangle.getY(), 0);
        assertEquals(rectangle.getWidth(), testRectangle.getWidth(), 0);
        assertEquals(rectangle.getHeight(), testRectangle.getHeight(), 0);
    }

    @Test
    public void testMouseReleased(){
        canvas.clearDummyDrawable();
        Point2D clickPoint = new Point2D.Double(0, 0);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mousePressed(e);

        clickPoint = new Point2D.Double(50, 50);
        e = new MouseEvent(canvas, MouseEvent.MOUSE_DRAGGED, 2, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseDragged(e);

        DrawableRectangle rectangle = (DrawableRectangle) canvas.getDummyDrawable();

        e = new MouseEvent(canvas, MouseEvent.MOUSE_RELEASED, 3, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseReleased(e);

        DrawableRectangle dummy = (DrawableRectangle) canvas.getDummyDrawable();
        assertNull(dummy);

        boolean inserted = canvas.getDrawing().containsDrawable(rectangle);
        assertTrue(inserted);
    }


    @Test
    public void testMouseReleasedHorizontalBoundary(){
        Point2D clickPoint = new Point2D.Double(0, 0);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mousePressed(e);

        clickPoint = new Point2D.Double(50, 0);
        e = new MouseEvent(canvas, MouseEvent.MOUSE_DRAGGED, 2, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseDragged(e);
        DrawableRectangle rectangle = (DrawableRectangle) canvas.getDummyDrawable();

        e = new MouseEvent(canvas, MouseEvent.MOUSE_RELEASED, 3, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseReleased(e);

        boolean inserted = canvas.getDrawing().containsDrawable(rectangle);
        assertFalse(inserted);

    }

    @Test
    public void testMouseReleasedVerticalBoundary(){
        Point2D clickPoint = new Point2D.Double(0, 0);
        MouseEvent e = new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mousePressed(e);

        clickPoint = new Point2D.Double(0, 50);
        e = new MouseEvent(canvas, MouseEvent.MOUSE_DRAGGED, 2, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseDragged(e);
        DrawableRectangle rectangle = (DrawableRectangle) canvas.getDummyDrawable();

        e = new MouseEvent(canvas, MouseEvent.MOUSE_RELEASED, 3, InputEvent.BUTTON1_DOWN_MASK, (int) clickPoint.getX(), (int) clickPoint.getY(), 1, false);
        rectangleTool.mouseReleased(e);

        boolean inserted = canvas.getDrawing().containsDrawable(rectangle);
        assertFalse(inserted);

    }
}