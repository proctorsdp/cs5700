package gui;


import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class DrawingPane extends Pane {

    private boolean isDirty = false;

    public DrawingPane() {}

    public ObservableList<Node> getElements() {
        return this.getChildren();
    }

    public void clear() {
        this.getChildren().clear();
        isDirty = true;
    }

    public void loadFromStream(Stream stream) {
        ArrayList<Node> loadedElements = null;

        if (loadedElements == null || loadedElements.size() == 0) {
            return;
        }

        for (Node element : loadedElements) {
            Node tmp = null;
            if (tmp != null) {
                //TODO
                this.getChildren().add(tmp);
            } else {
                this.getChildren().add(element);
            }
        }
        isDirty = true;
    }

    public void saveToStream(Stream stream) {

    }

    public void addNode(Node element) {
        if (element == null) { return; }
        this.getChildren().add(element);
        isDirty = true;
    }

    public void deleteNode(Node element) {
        if (element == null) { return; }
        this.getChildren().remove(element);
        isDirty = true;
    }

    public ArrayList<Node> deleteSelected() {
        ArrayList<Node> elementsToDelete = new ArrayList<>();
        for (Node element : this.getChildren()) {
//            if (element.isSelected()) {
//                elementsToDelete.add(element);
//                this.getChildren().remove(element);
//            }
        }
        isDirty = true;
        return elementsToDelete;
    }

    public Node findElementAtPosition(Point2D point) {
        Node result = null;
        for (Node element : this.getChildren()) {
//            if (element.containsPoint(point)) {
//                result = element;
//            }
        }
        return result;
    }

    public ArrayList<Node> deselectAll() {
        ArrayList<Node> selectedElements = new ArrayList<>();
        for (Node element : this.getChildren()) {
//            if (element.isSelected()) {
//                selectedElements.add(element);
//                element.isSelected() = false;
//            }
        }
        isDirty = true;
        return selectedElements;
    }

    public boolean draw(Pane drawingPane) throws IOException {
        if (!isDirty) { return false; }
        drawingPane.getChildren().clear();
        for (Node element : this.getChildren()) {
            drawingPane.getChildren().add(element);
        }
        isDirty = false;
        return true;
    }
}
