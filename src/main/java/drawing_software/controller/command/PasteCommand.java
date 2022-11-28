package drawing_software.controller.command;

import drawing_software.Context;
import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.util.Iterator;

public class PasteCommand implements Command {
    private CanvasView canvas;
    private Drawable copiedShape;

    public PasteCommand(CanvasView canvas, Drawable copiedShape) {
        this.canvas = canvas;
        this.copiedShape = copiedShape;
    }

    @Override
    public void execute() {
        System.out.println("Sei nel command di stampa, servirò per chiedere al receiver di incollare");

        if (copiedShape == null) {
            Context.getInstance().setSaved(false);
            System.out.println("Nessuna figura precedentemente selezionata");
        }
        System.out.println("Aggiungo");
        System.out.println(copiedShape);
        System.out.println("al drawing");
        canvas.getDrawing().addDrawable(copiedShape);
        Iterator<Drawable> itr = canvas.getDrawing().iterator();
        while (itr.hasNext()) {
            Shape s = (Shape) itr.next();
            System.out.println(s.getBounds());
        }
    }
}
