package test.controller;

import org.junit.Before;
import org.junit.Test;
import project_work.controller.command.ShapeCommand;
import project_work.model.Drawable;
import project_work.model.DrawableEllipse;
import project_work.model.DrawableLine;
import project_work.model.DrawableRectangle;
import project_work.view.CanvasView;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class ShapeCommandTest {

    private Drawable shape;
    private CanvasView canvas;

    @Before
    public void setUp() {
        canvas = new CanvasView();
    }

    @Test
    public void testExecuteWithLine() {
        Point2D p1 = new Point2D.Double(10, 10);
        Point2D p2 = new Point2D.Double(20, 20);
        shape = new DrawableLine(p1, p2);
        new ShapeCommand(canvas, shape).execute();
        assertTrue(canvas.getDrawing().containsDrawable(shape));
    }

    @Test
    public void testExecuteWithRectangle() {
        shape = new DrawableRectangle(10, 10);
        new ShapeCommand(canvas, shape).execute();
        assertTrue(canvas.getDrawing().containsDrawable(shape));
    }

    @Test
    public void testExecuteWithEllipse() {
        shape = new DrawableEllipse(10, 10);
        new ShapeCommand(canvas, shape).execute();
        assertTrue(canvas.getDrawing().containsDrawable(shape));
    }

}