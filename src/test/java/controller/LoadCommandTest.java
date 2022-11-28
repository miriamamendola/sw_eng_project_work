package controller;

import drawing_software.controller.command.Command;
import drawing_software.controller.command.LoadCommand;
import drawing_software.controller.command.SaveCommand;
import drawing_software.model.*;
import drawing_software.view.CanvasView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoadCommandTest {

    private CanvasView canvas;

    private File file;

    private Command loadCommand;

    @Before
    public void setUp() {
        JFrame frame = new JFrame();
        canvas = new CanvasView();
        frame.add(canvas);

        canvas.getDrawing().addDrawable(new DrawableRectangle(1, 1));
        canvas.getDrawing().addDrawable(new DrawableEllipse(4, 3));
        canvas.getDrawing().addDrawable(new DrawableLine(new Point2D.Double(10, 4), new Point2D.Double(20, 8)));

        file = new File("./testfile.draw");
        new SaveCommand(canvas, file).execute();

        loadCommand = new LoadCommand(canvas, file);
    }

    @Test
    public void testExecute() {
        canvas.setDrawing(new Drawing());
        loadCommand.execute();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Drawing saved = (Drawing) ois.readObject();
            List<Drawable> savedList = new LinkedList<>();
            for (Drawable d : saved) {
                savedList.add(d);
            }

            List<Drawable> current = new LinkedList<>();
            for (Drawable d : canvas.getDrawing()) {
                current.add(d);
            }

            assertEquals(savedList, current);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() {
        file.delete();
    }

}