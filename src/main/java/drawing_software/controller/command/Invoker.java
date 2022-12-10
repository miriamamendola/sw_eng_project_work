package drawing_software.controller.command;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Invoker class of the Command pattern. Through this class, ConcreteCommand can
 * be carried out by the receiver.
 * When the undo function will be implemented, it will hold a data structure
 * in order to memorize the executed commands.
 */
public class Invoker {

    private final Deque<Command> commands = new ArrayDeque<>();

    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * Allows the invoker to call for the execution of a command given as
     * an input parameter.
     *
     * @param command is the command to be executed by the receiver, and is
     *                given by the Client classed who want that command to be executed.
     */
    public void executeCommand(Command command) {
        commands.offerLast(command);
        command.execute();
        changes.firePropertyChange("IS_INVOKER_EMPTY", true, false);
    }

    public void undoLastCommand() {
        Command last = commands.pollLast();
        if (last != null) {
            last.undo();
        }
        if (commands.isEmpty()) {
            changes.firePropertyChange("IS_INVOKER_EMPTY", false, true);
        }

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changes.removePropertyChangeListener(listener);
    }

}
