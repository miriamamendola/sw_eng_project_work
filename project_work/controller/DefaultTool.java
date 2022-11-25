package project_work.controller;

import project_work.model.Drawable;
import project_work.model.Drawing;
import project_work.view.CanvasView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class DefaultTool implements Tool {
    private final CanvasView canvas;

    public DefaultTool(CanvasView canvas){
        this.canvas = canvas;
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point2D point = mouseEvent.getPoint();
        for (Drawable d: canvas.getDrawing()){
            Shape s = (Shape) d;
               if(s.contains(point)){
                canvas.setSelectedShape(s);
                break;
            }
        }

    }
}
