package src.controller.tool;

import java.awt.event.MouseEvent;

/**
 * This interface defines the behaviour of the CanvasView.
 * It corresponds to the "State" Interface of the State Pattern.
 */
public interface Tool {

    default void mousePressed(MouseEvent mouseEvent) {

    }

    default void mouseDragged(MouseEvent mouseEvent) {

    }

    default void mouseReleased(MouseEvent mouseEvent) {

    }

    default void mouseClicked(MouseEvent mouseEvent) {

    }
}
