package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

public class ForwardCommand implements Command {
    private CanvasView canvas;
    private int oldIndex;
    public ForwardCommand(CanvasView canvas) {
        this.canvas = canvas;
        this.oldIndex = canvas.getDrawing().getDrawableIndex(canvas.getSelectionGrid().getSelectedShape());
    }

    @Override
    public void execute() {
        Drawable toLast = canvas.getSelectionGrid().getSelectedShape();
        canvas.getDrawing().removeDrawable(toLast);
        canvas.getDrawing().addDrawable(toLast);
        canvas.repaint();
    }
}
