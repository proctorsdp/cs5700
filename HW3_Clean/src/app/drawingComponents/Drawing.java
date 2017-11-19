package app.drawingComponents;

import gui.ToolBar;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@XmlRootElement(name = "drawing")
public class Drawing extends ArrayList<Element> {

    private boolean isDirty = false;

    private static Drawing instance;

    private static final Object lock = new Object();

    private Drawing() {}

    public static Drawing getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new Drawing();
            }
        }
        return instance;
    }

    @XmlElement(name = "element")
    public void setElements(List<Element> elements) { this.addAll(elements); }

    public List<Element> getElements() {
        return this;
    }

    public void clear() {
        this.removeAll(this);
        isDirty = true;
    }

    public void loadFromStream(String file, ToolBar toolBar) throws JAXBException {
        this.clear();
        JAXBContext context = JAXBContext.newInstance(Drawing.class);
        Unmarshaller unMar = context.createUnmarshaller();
        this.addAll((Drawing) unMar.unmarshal(new File(file)));
        isDirty = true;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reconnectLines(toolBar);
            }
        }, 2000);
    }

    public void saveToStream(String file) throws JAXBException {
        updateLineLocations();
        JAXBContext context = JAXBContext.newInstance(Drawing.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(this, new File(file));
    }

    public void addNodeToFront(Element element) {
        if (element == null) { return; }
        this.add(element);
        isDirty = true;
    }

    public void addNodeToBack(Element element) {
        if (element == null) { return; }
        this.add(0, element);
        isDirty = true;
    }

    public void deleteNode(Element element) {
        if (element == null) { return; }
        this.remove(element);
        isDirty = true;
    }

    public ArrayList<Element> deleteRelationships(Node selected) {
        ArrayList<Element> elementsToDelete = new ArrayList<>();
        for (Element element : this) {
            if (selected.getBoundsInParent().intersects(element.getBounds())) {
                elementsToDelete.add(element);
            }
        }
        isDirty = true;
        return elementsToDelete;
    }

    public Node findElementAtPosition(Point2D point) {
        Element result = null;
        for (Element element : this) {
            if (element.getBounds().contains(point)) {
                result = element;
            }
        }
        return result == null ? null : result.getNode();
    }

    //TODO
//    public ArrayList<Element> deselectAll() {
//        ArrayList<Element> selectedElements = new ArrayList<>();
//        for (Element element : this) {
//            if (element.isSelected()) {
//                selectedElements.add(element);
//                element.isSelected() = false;
//            }
//        }
//        isDirty = true;
//        return selectedElements;
//    }

    public boolean draw(Pane drawingPane) {
        if (!isDirty) { return false; }
        Platform.runLater(() -> {
            addToPane(drawingPane);
        });
        return true;
    }

    private void addToPane(Pane drawingPane) {
        drawingPane.getChildren().clear();
        for (Element element : this) {
            drawingPane.getChildren().add(element.getNode());
        }
        isDirty = false;
    }

    private void updateLineLocations() {
        for (Element n : this) {
            if (n instanceof RelationshipLine) {
                ((RelationshipLine) n).updateLocationData();
            }
        }
    }

    private void reconnectLines(ToolBar toolBar) {
        for (Element element : this) {
            if (element instanceof ClassBox) {
                ((ClassBox) element).setToolBar(toolBar);
            } else if (element instanceof RelationshipLine) {
                Node startNode = null;
                Node endNode = null;

                Location startLocation = ((RelationshipLine) element).getStartLocation();
                Location endLocation = ((RelationshipLine) element).getEndLocation();
                Point2D startPoint = new Point2D(startLocation.getX(), startLocation.getY());
                Point2D endPoint = new Point2D(endLocation.getX(), endLocation.getY());

                for (Element n : this) {
                    if (n instanceof RelationshipLine) {
                        continue;
                    } else if (n.getBounds().contains(startPoint)) {
                        startNode = n.getNode();
                    } else if (n.getBounds().contains(endPoint)) {
                        endNode = n.getNode();
                    }
                }

                if (startNode != null && endNode != null) {
                    ((RelationshipLine) element).bindToNodes(startNode, endNode);
                }
            }
        }
    }
}
