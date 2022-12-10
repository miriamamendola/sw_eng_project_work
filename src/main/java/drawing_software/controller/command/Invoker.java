package drawing_software.controller.command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Invoker class of the Command pattern. Through this class, ConcreteCommand can
 * be carried out by the receiver.
 * When the undo function will be implemented, it will hold a data structure
 * in order to memorize the executed commands.
 */
public class Invoker {

    private Deque<Command> commands = new ArrayDeque<>();

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
    }

    public void undoLastCommand() {
        Command last = commands.pollLast();
        if (last != null) {
            last.undo();
        }

    }
}
