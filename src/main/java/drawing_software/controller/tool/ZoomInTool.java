package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;


public class ZoomInTool implements Tool{
    private final CanvasView canvas;
    private final Invoker invoker;
    public ZoomInTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
    }


    }
