package drawing_software.controller.tool;


import drawing_software.controller.command.CutCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

/**
 * Defines the behaviour of the canvas when the Cut Tool is selected, and further implements the State pattern.
 * When this state is selected, the user can click and cut a previously selected shape. This will remove the
 * shape in order to paste it at a second moment.
 * <p>
 * The invoker, as a parameter, is necessary for the Tool to request the execution of the corresponding
 * command to the Canvas (alias the Receiver).
 */
public class CutTool implements Tool {
    private final CanvasView canvas;
    private final Invoker invoker;


    public CutTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
        invoker.executeCommand(new CutCommand(canvas));
    }
}
