package drawing_software.controller.tool;

import drawing_software.controller.command.Invoker;
import drawing_software.view.CanvasView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;


public class ZoomInTool implements Tool{
    private final CanvasView canvas;
    private final Invoker invoker;
    private double zoomFactor = 1;
    private double prevZoomFactor = 1;

    private double xOffset = 0;
    private double yOffset = 0;
    public ZoomInTool(CanvasView canvas, Invoker invoker) {
        this.canvas = canvas;
        this.invoker = invoker;
       
    }

    @Override
    public void mouseLeftClicked(MouseEvent e) {

        System.out.println("click");
        AffineTransform at = new AffineTransform();
        zoomFactor *= 1.1;
        /*double xRel = e.getX() - e.getLocationOnScreen().getX();
        double yRel = e.getY() - e.getLocationOnScreen().getY();
        System.out.println(e.getX());
        System.out.println(xRel);
        System.out.println(e.getLocationOnScreen().getX());
        double zoomDiv = zoomFactor / prevZoomFactor;

        xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
        yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

        at.translate(xOffset, yOffset);
        at.scale(zoomFactor, zoomFactor);
        prevZoomFactor = zoomFactor;*/
        at.translate(e.getX(),e.getY());
        at.scale(zoomFactor,zoomFactor);
        at.translate(-e.getX(),-e.getY());

        canvas.setScalePoint(e.getPoint());
        canvas.setAt(at);
        canvas.repaint();
    }

}
