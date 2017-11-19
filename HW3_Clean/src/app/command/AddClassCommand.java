package app.command;

import app.drawingComponents.Size;
import app.drawingComponents.ClassBox;
import gui.ToolBar;
import javafx.application.Platform;
import javafx.geometry.Point2D;


public class AddClassCommand extends Command {

    private String label;
    private Point2D location;
    private Size size;
    private ToolBar toolBar;
    private ClassBox classBox;

    public AddClassCommand(Object[] commandParameters) {
        if (commandParameters.length > 0) {
            label = (String) commandParameters[0];
        }

        if (commandParameters.length > 1) {
            location = (Point2D) commandParameters[1];
        }

        if (commandParameters.length > 2) {
            size = (Size) commandParameters[2];
        }

        if (commandParameters.length > 3) {
            toolBar = (ToolBar) commandParameters[3];
        }
    }

    @Override
    public boolean execute() {
        classBox = new ClassBox();
        classBox.setToolBar(toolBar);
        classBox.setLocation(location);
        classBox.resize(size);
        targetDrawing.addNodeToFront(classBox);

        return true;
    }

    @Override
    public void undo() {
        Platform.runLater(() -> {
            targetDrawing.deleteNode(classBox);
        });
    }

    @Override
    public void redo() {
        Platform.runLater(() -> {
            targetDrawing.addNodeToFront(classBox);
        });
    }
}
