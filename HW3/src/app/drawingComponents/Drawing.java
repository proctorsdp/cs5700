package app.drawingComponents;


import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Drawing {

    private ArrayList<Element> elements = new ArrayList<>();

    public boolean isDirty = true;

    public ArrayList<Element> getElements() {
        return (ArrayList<Element>) elements.clone();
    }

    public void clear() {
        elements.clear();
        isDirty = true;
    }

    public void loadFromStream(Stream stream) {
        ArrayList<Element> loadedElements = null;

        if (loadedElements == null || loadedElements.size() == 0) {
            return;
        }

        for (Element element : loadedElements) {
            Element tmp = null;
            if (tmp != null) {
                //TODO
                elements.add(tmp);
            } else {
                elements.add(element);
            }
        }
        isDirty = true;
    }

    public void saveToStream(Stream stream) {

    }

    public void addElement(Element element) {
        if (element == null) { return; }
        elements.add(element);
        isDirty = true;
    }

    public void deleteElement(Element element) {
        if (element == null) { return; }
        elements.remove(element);
        isDirty = true;
    }

    public ArrayList<Element> deleteSelected() {
        ArrayList<Element> elementsToDelete = new ArrayList<>();
        for (Element element : elements) {
            if (element.isSelected) {
                elementsToDelete.add(element);
                elements.remove(element);
            }
        }
        isDirty = true;
        return elementsToDelete;
    }

    public Element findElementAtPosition(Point2D point) {
        Element result = null;
        for (Element element : elements) {
            if (element.containsPoint(point)) {
                result = element;
            }
        }
        return result;
    }

    public ArrayList<Element> deselectAll() {
        ArrayList<Element> selectedElements = new ArrayList<>();
        for (Element element : elements) {
            if (element.isSelected) {
                selectedElements.add(element);
                element.isSelected = false;
            }
        }
        isDirty = true;
        return selectedElements;
    }

    public boolean draw(Pane drawingPane) {
        if (!isDirty) { return false; }
        drawingPane.getChildren().clear();
        //graphics.clear(Color.WHITE);
        for (Element element : elements) {
            //drawingPane.getChildren().add(element);
            //element.draw(graphics);
        }
        isDirty = false;
        return true;
    }
}