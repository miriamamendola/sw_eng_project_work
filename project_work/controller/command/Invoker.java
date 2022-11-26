package project_work.controller.command;

public class Invoker {
    public void executeCommand(Command command) {
        command.execute();
    }
}
