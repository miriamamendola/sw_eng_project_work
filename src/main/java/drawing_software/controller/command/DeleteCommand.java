package drawing_software.controller.command;

import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

public class DeleteCommand implements Command {

    private final CanvasView canvas;

    public DeleteCommand(CanvasView canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        canvas.getDrawing().removeDrawable((Shape) canvas.getSelectionGrid().getSelectedShape());
        canvas.clearSelectedDrawable();
        canvas.repaint();
    }
}
