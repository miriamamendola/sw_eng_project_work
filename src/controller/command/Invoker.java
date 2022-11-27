package src.controller.command;

/**
 * Invoker class of the Command pattern.
 * When the undo function will be implemented, it will hold a data structure
 * in order to memorize the executed commands.
 */
public class Invoker {
    public void executeCommand(Command command) {
        command.execute();
    }
}
