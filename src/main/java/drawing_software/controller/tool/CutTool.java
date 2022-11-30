package drawing_software.controller.tool;


import drawing_software.controller.command.CutCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;


public class CutTool implements Tool {
    private final CanvasView canvas;
    private final Invoker invoker;


    public CutTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
        invoker.executeCommand(new CutCommand(canvas));
    }
}
