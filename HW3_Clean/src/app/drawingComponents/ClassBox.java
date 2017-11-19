package app.drawingComponents;

import gui.ToolBar;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "classBox")
@XmlType(propOrder = { "description", "name", "size", "location" })
public class ClassBox extends Element {

    private String name = "Class";

    private Size size;

    private Location location;

    private transient TextField label;

    private transient Region body;

    private transient ToolBar toolBar;

    private transient VBox vBox;

    private transient Point2D origPoint;

    private static final int LABEL_HEIGHT = 40;

    private static final String classStyle = "-fx-background-color: dodgerblue; -fx-border-color: black; -fx-border-width: 3";


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        label.setText(name);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size vBoxSize) {
        this.size = vBoxSize;
        resize(vBoxSize);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        vBox.setTranslateX(location.getX());
        vBox.setTranslateY(location.getY());
    }

    @XmlTransient
    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    @Override
    public Node getNode() { return vBox; }


    public ClassBox() {
        setDescription("Class");
        initTextField();
        initRegion();
        initVBox();
    }

    private void initVBox() {
        vBox = new VBox(label, body);
        vBox.setFillWidth(true);
        vBox.setOnMousePressed(this::getOrigin_OnMousePressed);
        vBox.setOnMouseDragged(this::moveBox_OnMouseDragged);
    }

    private void initTextField() {
        label = new TextField(name);
        label.setAlignment(Pos.CENTER);
        label.setEditable(false);
        label.setStyle(classStyle);
        label.setPrefHeight(LABEL_HEIGHT);

        label.setOnKeyReleased(this::saveLabel_OnEnterKey);
        label.setOnMouseClicked(this::setLabelEditable_OnDoubleClick);
        label.setOnMousePressed(this::getOrigin_OnMousePressed);
        label.setOnMouseDragged(this::moveBox_OnMouseDragged);
    }

    private void initRegion() {
        body = new Region();
        body.setStyle(classStyle);
        VBox.setVgrow(body, Priority.ALWAYS);
    }

    public void resize(Size size) {
        this.size = size;
        vBox.setPrefWidth(size.getWidth());
        vBox.setPrefHeight(size.getHeight());
        body.setPrefHeight(size.getHeight() - LABEL_HEIGHT);
        label.setPrefWidth(size.getWidth());
    }

    public void setLocation(Point2D origin) {
        location = new Location(origin.getX(), origin.getY());
        vBox.setTranslateX(origin.getX());
        vBox.setTranslateY(origin.getY());
    }

    private void moveBox_OnMouseDragged(MouseEvent event) {
        if (!toolBar.isEditBtnSelected()) { return; }
        vBox.setTranslateX(vBox.getTranslateX() + event.getX() - origPoint.getX());
        vBox.setTranslateY(vBox.getTranslateY() + event.getY() - origPoint.getY());
        location = new Location(vBox.getTranslateX(), vBox.getTranslateY());
    }

    private void saveLabel_OnEnterKey(KeyEvent event) {
        if (!toolBar.isEditBtnSelected()) { return; }
        if (event.getCode() == KeyCode.ENTER) {
            label.commitValue();
            label.deselect();
            label.setEditable(false);
            name = label.getText();
        }
    }

    private void getOrigin_OnMousePressed(MouseEvent event) {
        if (!toolBar.isEditBtnSelected()) { return; }
        origPoint = new Point2D(event.getX(), event.getY());
    }

    private void setLabelEditable_OnDoubleClick(MouseEvent event) {
        if (!toolBar.isEditBtnSelected()) { return; }
        if (event.getClickCount() == 2) {
            label.setEditable(true);
        }
    }

    @Override
    public Bounds getBounds() {
        return vBox.getBoundsInParent();
    }

    @Override
    public double getX() {
        return vBox.getTranslateX();
    }

    @Override
    public double getY() {
        return vBox.getTranslateY();
    }
}
