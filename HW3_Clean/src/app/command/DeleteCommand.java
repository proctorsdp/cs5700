package app.command;

import app.drawingComponents.Element;
import app.drawingComponents.RelationshipLine;
import javafx.application.Platform;
import javafx.scene.Node;
import java.util.ArrayList;

public class DeleteCommand extends Command {

    private String label;

    private ArrayList<Element> nodes;

    private Node selectedNode;

    public DeleteCommand(Object[] commandParameters) {
        if (commandParameters.length > 0) {
            label = (String) commandParameters[0];
        }

        if (commandParameters.length > 1) {
            selectedNode = (Node) commandParameters[1];
        }
    }

    @Override
    public boolean execute() {
        nodes = targetDrawing.deleteRelationships(selectedNode);
        Platform.runLater(() -> {
            for (Element n : nodes) {
                targetDrawing.deleteNode(n);
            }
        });
        return true;
    }

    @Override
    public void undo() {
        for (Element n : nodes) {
            if (n instanceof RelationshipLine) {
                Platform.runLater(() -> targetDrawing.addNodeToBack(n));
            } else {
                Platform.runLater(() -> { targetDrawing.addNodeToFront(n); });
            }
        }
    }

    @Override
    public void redo() {
        Platform.runLater(() -> {
            for (Element n : nodes) {
                targetDrawing.deleteNode(n);
            }
    }); }
}
