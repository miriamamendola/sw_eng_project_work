package drawing_software.controller.command;

import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import java.util.logging.Logger;

public class DeleteCommand implements Command {

    private final CanvasView canvas;

    private Shape selectedShape;

    public DeleteCommand(CanvasView canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        updateTitle(canvas);
        try {
            selectedShape = (Shape) canvas.getSelectionGrid().getSelectedShape();
            canvas.getDrawing().removeDrawable(selectedShape);
            canvas.clearSelectedDrawable();
            canvas.repaint();
        } catch (NullPointerException e) {
            Logger.getLogger("root").warning("Delete command: no selected shape");
        }
    }

    @Override
    public void undo() {
        if (selectedShape != null) {
            canvas.getDrawing().addDrawable(selectedShape);
            canvas.clearSelectedDrawable();
            canvas.repaint();
        }
    }
}
