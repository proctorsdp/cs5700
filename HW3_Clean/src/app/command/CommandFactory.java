package app.command;

import app.drawingComponents.Drawing;
import app.invoker.Invoker;

public class CommandFactory {

    private static CommandFactory instance;

    private Drawing targetDrawing;

    private Invoker invoker;

    private CommandFactory() {}

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Drawing getTargetDrawing() {
        return targetDrawing;
    }

    public void setTargetDrawing(Drawing targetDrawing) {
        this.targetDrawing = targetDrawing;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    public void createAndDo(String commandType, Object[] commandParameters) {
        if (commandType.isEmpty()) { return; }

        if (targetDrawing == null) { return; }

        Command command = null;

        switch (commandType.trim().toUpperCase()) {
            case "NEW":
                command = new NewCommand(commandParameters);
                break;
            case "NEW_CLASS":
                command = new AddClassCommand(commandParameters);
                break;
            case "NEW_LINE":
                command = new AddLineCommand(commandParameters);
                break;
            case "MOVE_OBJECT":
                command = new MoveObjectCommand(commandParameters);
                break;
            case "DELETE_OBJECT":
                command = new DeleteCommand(commandParameters);
                break;
            case "CHANGE_COLOR":
                command = new ChangeColorCommand(commandParameters);
                break;
        }

        if (command != null) {
            command.setTargetDrawing(targetDrawing);
            invoker.enqueueCommandForExecution(command);
        }
    }
}
