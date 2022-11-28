package drawing_software.controller.tool;


import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import java.awt.event.MouseEvent;



public class PasteTool implements Tool {
    private final CanvasView canvasView;
    private final Invoker invoker;


    public PasteTool(CanvasView canvasView, Invoker invoker) {
        this.canvasView = canvasView;
        this.invoker= invoker;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("incolla");
    }
}

