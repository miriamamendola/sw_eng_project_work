package drawing_software.controller.tool;

import drawing_software.model.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class MouseRequest {

    private MouseEvent mouseEvent;

    private Shape selectedShape;
    private Point2D oldShapeLocation;
    private Dimension oldShapeSize;
    private Point2D prevMouse;
    private Point2D startingPoint;
    private double ratio;

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }

    public void setMouseEvent(MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
    }

    public Point2D getOldShapeLocation() {
        return oldShapeLocation;
    }

    public void setOldShapeLocation(Point2D oldShapeLocation) {
        this.oldShapeLocation = oldShapeLocation;
    }

    public Dimension getOldShapeSize() {
        return oldShapeSize;
    }

    public void setOldShapeSize(Dimension oldShapeSize) {
        this.oldShapeSize = oldShapeSize;
    }

    public Point2D getPrevMouse() {
        return prevMouse;
    }

    public void setPrevMouse(Point2D prevMouse) {
        this.prevMouse = prevMouse;
    }

    public Point2D getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Point2D startingPoint) {
        this.startingPoint = startingPoint;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
