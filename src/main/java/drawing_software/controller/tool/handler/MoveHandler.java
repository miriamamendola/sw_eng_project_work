package drawing_software.controller.tool.handler;

import drawing_software.controller.tool.MouseRequest;
import drawing_software.model.SelectionGrid;
import drawing_software.model.Shape;
import drawing_software.view.Canvas;

import java.awt.event.MouseEvent;

public class MoveHandler extends Handler {

    public MoveHandler(Canvas canvas) {
        super(canvas);
    }

    @Override
    void handlePressed(MouseRequest request) {

    }

    @Override
    void handleDragged(MouseRequest request) {
        MouseEvent mouseEvent = request.getMouseEvent();

        int delta_x = (int) (mouseEvent.getX() - request.getPrevMouse().getX());
        int delta_y = (int) (mouseEvent.getY() - request.getPrevMouse().getY());

        Shape selectedShape = request.getSelectedShape();

        selectedShape.setLocation(selectedShape.getBounds().getX() + delta_x, selectedShape.getBounds().getY() + delta_y);
        canvas.clearSelectedDrawable();
        canvas.setSelectionGrid(new SelectionGrid(selectedShape));
        request.setPrevMouse(mouseEvent.getPoint());
        canvas.repaint();
    }

}
