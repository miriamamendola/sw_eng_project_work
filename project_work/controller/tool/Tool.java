package project_work.controller.tool;

import java.awt.event.MouseEvent;

public interface Tool {

    void mousePressed(MouseEvent mouseEvent);

    void mouseDragged(MouseEvent mouseEvent);

    void mouseReleased(MouseEvent mouseEvent);

    void mouseClicked(MouseEvent mouseEvent);
}
