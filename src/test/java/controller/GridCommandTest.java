package controller;

import drawing_software.controller.command.GridCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GridCommandTest {

    private CanvasView canvas;
    private Invoker invoker;
    private GridCommand gc;

    @Before
    public void setUp() {
        invoker = new Invoker();
        canvas = new CanvasView(invoker);
        gc = new GridCommand(canvas, 30);
    }

    @Test
    public void testExecute() {
        gc.execute();
        assertNotNull(canvas.getGrid());
        assertEquals(30, canvas.getGrid().getCellSize());
    }
}
