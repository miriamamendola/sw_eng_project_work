package drawing_software.controller.command;

/**
 * Invoker class of the Command pattern. Through this class, ConcreteCommand can
 * be carried out by the receiver.
 * When the undo function will be implemented, it will hold a data structure
 * in order to memorize the executed commands.
 */
public class Invoker {
    /**
     * Allows the invoker to call for the execution of a command given as
     * an input parameter.
     *
     * @param command is the command to be executed by the receiver, and is
     *                given by the Client classed who want that command to be executed.
     */
    public void executeCommand(Command command) {
        command.execute();
    }
}
