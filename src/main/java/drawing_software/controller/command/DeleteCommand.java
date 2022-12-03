package drawing_software.controller.command;

import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import java.util.logging.Logger;

public class DeleteCommand implements Command {

    private final CanvasView canvas;

    public DeleteCommand(CanvasView canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute() {
        try {
            Shape selectedShape = (Shape) canvas.getSelectionGrid().getSelectedShape();

            canvas.getDrawing().removeDrawable(selectedShape);
            canvas.clearSelectedDrawable();
            canvas.repaint();
        } catch (NullPointerException e) {
            Logger.getLogger("root").warning("Delete command: no selected shape");
        }
    }
}
