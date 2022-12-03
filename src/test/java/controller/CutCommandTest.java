package controller;

import drawing_software.controller.command.CutCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.controller.tool.SelectionTool;
import drawing_software.model.Drawable;
import drawing_software.model.DrawableEllipse;
import drawing_software.model.SelectionGrid;
import drawing_software.model.Shape;
import drawing_software.view.CanvasView;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class CutCommandTest {
    private CanvasView canvas;
    private SelectionTool st;
    private Invoker invoker;
    private CutCommand ct;
    private DrawableEllipse dr;

    @Before
    public void setUp() throws Exception {
        invoker = new Invoker();
        canvas = new CanvasView(invoker);
        st = new SelectionTool(canvas, invoker);
        ct = new CutCommand(canvas);
        dr = new DrawableEllipse(Color.cyan, Color.gray, 40, 40);
    }

    @Test
    public void testExecute() throws IOException, UnsupportedFlavorException, ClassNotFoundException {
        canvas.setSelectionGrid(new SelectionGrid(dr));
        canvas.getSelectionGrid().setSelectedShape(dr);
        ct.execute();
        DataFlavor dataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=\"" + Drawable.class.getName() + "\"");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tr = clipboard.getContents(this);
        drawing_software.model.Shape copiedShape = (Shape) tr.getTransferData(dataFlavor);
        assertFalse(canvas.getDrawing().removeDrawable(dr));
        assertEquals(dr, copiedShape);
    }
}
