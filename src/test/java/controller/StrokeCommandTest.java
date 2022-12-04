package controller;

import drawing_software.controller.command.Invoker;
import drawing_software.controller.command.StrokeCommand;
import drawing_software.model.DrawableRectangle;
import drawing_software.model.SelectionGrid;
import drawing_software.view.CanvasView;
import drawing_software.view.colors.ColorButton;
import drawing_software.view.colors.FillPanel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;

public class StrokeCommandTest {
    private CanvasView canvas;

    private ColorButton strokeButton;
    private DrawableRectangle rectangle;

    @Before
    public void setUp() throws Exception {
        JFrame frame = new JFrame();
        Invoker invoker = new Invoker();
        canvas = new CanvasView(invoker);
        frame.add(canvas);
        FillPanel panel = (FillPanel) new FillPanel(canvas, invoker).createPanel();
        strokeButton = panel.getButton();
        rectangle = new DrawableRectangle(0, 0);
        rectangle.setSize(new Dimension(30, 30));
        canvas.getDrawing().addDrawable(rectangle);
        canvas.setSelectionGrid(new SelectionGrid(rectangle));
    }

    @Test
    public void execute() {
        StrokeCommand command = new StrokeCommand(canvas, Color.blue);
        command.execute();
        assertEquals(rectangle.getStrokeColor(), Color.blue);
    }
}