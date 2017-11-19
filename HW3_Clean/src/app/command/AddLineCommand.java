package app.command;

import app.drawingComponents.Element;
import app.drawingComponents.RelationshipLine;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class AddLineCommand extends Command {

    private String label;

    private String lineType;

    private Node startNode;

    private Node endNode;

    private RelationshipLine newLine;

    private ArrayList<Element> lineElements = new ArrayList<>();

    public AddLineCommand(Object[] commandParameters) {
        if (commandParameters.length > 0) {
            label = (String) commandParameters[0];
        }

        if (commandParameters.length > 1) {
            startNode = (Node) commandParameters[1];
        }

        if (commandParameters.length > 2) {
            endNode = (Node) commandParameters[2];
        }

        if (commandParameters.length > 3) {
            lineType = ((String) commandParameters[3]).trim().toUpperCase();
        }
    }

    @Override
    public boolean execute()  {
        if (startNode != null && endNode != null) {
            newLine = new RelationshipLine();
            newLine.setLineType(lineType);
            newLine.bindToNodes(startNode, endNode);
            lineElements.add(newLine);
        }

        addLine();
        return true;
    }

    @Override
    public void undo() {
        Platform.runLater(() -> {
            for (Element n : lineElements) {
                targetDrawing.deleteNode(n);
            }
        });
    }

    @Override
    public void redo() { addLine(); }

    private void addLine() {
        Platform.runLater(() -> {
            for (Element n : lineElements) {
                if (n instanceof RelationshipLine) {
                    targetDrawing.addNodeToBack(n);
                } else {
                    targetDrawing.addNodeToFront(n);
                }
            }
        });
    }

//    private void modifyLine() {
//        switch (lineType) {
//            case "BINARY":
//                textField = new Text("Label");
//                textField.setStyle("-fx-background-color: transparent");
//                textField.setStyle("-fx-border-color: transparent");
//                textField.translateXProperty().bind(newLine.startXProperty().add((newLine.getEndX()-newLine.getStartX()) / 2));
//                textField.translateYProperty().bind(newLine.startYProperty().add((newLine.getEndY()-newLine.getStartY()) / 2));
//                lineElements.add(textField);
//                break;
//            case "GENERAL":
//                break;
//            case "DEPENDENCY":
//                newLine.setStyle("-fx-stroke-dash-array: 20");
//                break;
//            case "AGGREGATION":
//                break;
//            case "COMPOSITION":
//                break;
//        }
//    }
}
