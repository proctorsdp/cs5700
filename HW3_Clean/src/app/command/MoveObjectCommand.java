package app.command;

import app.drawingComponents.Size;
import gui.ToolBar;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class MoveObjectCommand extends Command {

    private String label;

    private Point2D oldPoint;

    private Point2D newPoint;

    private Node node;

    MoveObjectCommand(Object [] commandParameters) {
        if (commandParameters.length > 0) {
            label = (String) commandParameters[0];
        }

        if (commandParameters.length > 1) {
            oldPoint = (Point2D) commandParameters[1];
        }

        if (commandParameters.length > 2) {
            newPoint = (Point2D) commandParameters[2];
        }

        if (commandParameters.length > 3) {
            node = (Node) commandParameters[3];
        }
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void undo() {
        Platform.runLater(() -> {
            node.setTranslateX(oldPoint.getX());
            node.setTranslateY(oldPoint.getY());
        });
    }

    @Override
    public void redo() {
        Platform.runLater(() -> {
            node.setTranslateX(newPoint.getX());
            node.setTranslateY(newPoint.getY());
        });
    }
}
