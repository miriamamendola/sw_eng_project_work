package view;

import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;
import org.junit.Before;
import org.junit.Test;

public class FillPanelTest {
    private Invoker invoker;

    private CanvasView canvas;

    @Before
    public void setUp() throws Exception {
        invoker = new Invoker();
        canvas = new CanvasView(invoker);

    }

    @Test
    public void testChangeCanvasFillColor() {
    }

    @Test
    public void testChangeShapeFillColor() {
    }
}