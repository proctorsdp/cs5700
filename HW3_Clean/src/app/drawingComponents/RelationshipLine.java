package app.drawingComponents;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.Line;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "relationshipLine")
@XmlType(propOrder = { "description", "lineType", "startLocation", "endLocation" })
public class RelationshipLine extends Element {

    private String lineType;

    private Location startLocation;

    private Location endLocation;

    private transient Line line;


    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
        line.setStartX(startLocation.getX());
        line.setStartY(startLocation.getY());
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
        line.setEndX(endLocation.getX());
        line.setEndY(endLocation.getY());
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
        modifyLine();
    }

    @Override
    public Line getNode() { return line; }

    public RelationshipLine() {
        line = new Line();
        line.setStrokeWidth(3);
        setDescription("Relationship");
    }

    public void modifyLine() {
        switch (lineType) {
            case "BINARY":
//                textField = new Text("Label");
//                textField.setStyle("-fx-background-color: transparent");
//                textField.setStyle("-fx-border-color: transparent");
//                textField.translateXProperty().bind(newLine.startXProperty().add((newLine.getEndX()-newLine.getStartX()) / 2));
//                textField.translateYProperty().bind(newLine.startYProperty().add((newLine.getEndY()-newLine.getStartY()) / 2));
//                lineElements.add(textField);
                break;
            case "GENERAL":
                break;
            case "DEPENDENCY":
                line.setStyle("-fx-stroke-dash-array: 20");
                break;
            case "AGGREGATION":
                break;
            case "COMPOSITION":
                break;
        }
    }

    public void bindToNodes(Node startNode, Node endNode) {
        line.startXProperty().bind(startNode.translateXProperty().add(startNode.getBoundsInParent().getWidth() / 2));
        line.startYProperty().bind(startNode.translateYProperty().add(startNode.getBoundsInParent().getHeight() / 2));
        line.endXProperty().bind(endNode.translateXProperty().add(endNode.getBoundsInParent().getWidth() / 2));
        line.endYProperty().bind(endNode.translateYProperty().add(endNode.getBoundsInParent().getHeight() / 2));
        updateLocationData();
    }

    public void updateLocationData() {
        startLocation = new Location(line.getStartX(), line.getStartY());
        endLocation = new Location(line.getEndX(), line.getEndY());
    }

    @Override
    public Bounds getBounds() {
        return line.getBoundsInParent();
    }

    @Override
    public double getX() {
        return line.getTranslateX();
    }

    @Override
    public double getY() {
        return line.getTranslateY();
    }
}
