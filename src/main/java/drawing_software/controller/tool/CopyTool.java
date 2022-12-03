package drawing_software.controller.tool;

import drawing_software.controller.command.CopyCommand;
import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import java.awt.*;


public class CopyTool implements Tool {
    private final CanvasView canvasView;
    private final Invoker invoker;

    public Shape copiedShape;

    public CopyTool(CanvasView canvasView, Invoker invoker) {
        this.canvasView = canvasView;
        this.invoker = invoker;
        invoker.executeCommand(new CopyCommand(canvasView));
    }

}
