package drawing_software.controller.command;

import drawing_software.Context;
import drawing_software.Main;
import drawing_software.view.Canvas;

import javax.swing.*;
import java.io.File;

/**
 * First step in implementing the Command design pattern; this interface is to
 * be then implemented by all the concrete commands that will then overwrite
 * the method (which is usually devoid of input parameters) execute().
 */
public interface Command {
    /**
     * When implemented by the ConcreteCommands, will allow the Invoker to
     * be independent of how the client's request is performed; that is, the Invoker
     * will not need to have beforehand knowledge of what command is being requested
     * and the specific parameters involved. This also allows for the Invoker's behaviour
     * to be changed at runtime.
     */
    void execute();

    default void updateTitle(Canvas canvas) {
        Context.getInstance().setSaved(false);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(canvas);
        File f = Context.getInstance().getCurrentFile();
        if (f == null) {
            frame.setTitle("untitled" + " * - " + Main.appTitle);
        } else {
            frame.setTitle(f.getName() + " * - " + Main.appTitle);
        }
    }

    default void undo() {
        throw new UnsupportedOperationException();
    }

}