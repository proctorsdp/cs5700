package app.command;

import app.drawingComponents.Drawing;
import gui.DrawingPane;

public class CommandFactory {

    private static CommandFactory instance;
    private CommandFactory() {}

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    private DrawingPane targetDrawing;
    private Invoker invoker;

    public DrawingPane getTargetDrawing() {
        return targetDrawing;
    }

    public void setTargetDrawing(DrawingPane targetDrawing) {
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
            case "SAVE":

                break;
            case "LOAD":

                break;
            case "SELECT":

                break;
            case "DESELECT":

                break;
            case "REMOVE":

                break;
            case "ADD_CLASS":
                command = new AddBoxCommand(commandParameters);
                break;
        }

        if (command != null) {
            command.setTargetDrawing(targetDrawing);
            invoker.enqueueCommandForExecution(command);
        }
    }
}
