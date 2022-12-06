package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

public class BackwardCommand implements Command{
    private CanvasView canvas;
    private int oldIndex;
    public BackwardCommand(CanvasView canvas) {
        this.canvas = canvas;
        this.oldIndex =  canvas.getDrawing().getDrawableIndex(canvas.getSelectionGrid().getSelectedShape());
    }

    @Override
    public void execute() {
    Drawable toFirst= canvas.getSelectionGrid().getSelectedShape();
    canvas.getDrawing().removeDrawable(toFirst);
    canvas.getDrawing().addDrawableFirst(toFirst);
    canvas.repaint();

    }
}
