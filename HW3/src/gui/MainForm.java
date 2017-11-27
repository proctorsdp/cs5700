
package gui;

import app.command.CommandFactory;
import app.command.Invoker;
import app.drawingComponents.Drawing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainForm {

    private Timer refreshTimer = new Timer();

    private Point2D origPoint;

    private Shape shapeGuide;

    private CommandFactory commandFactory = CommandFactory.getInstance();

    private Invoker invoker = new Invoker();

    private DrawingPane drawingPane;

    public MainForm() {
        drawingPane = new DrawingPane();
        commandFactory.setInvoker(invoker);
        commandFactory.setTargetDrawing(drawingPane);
        //borderPane.setCenter(drawingPane);
        Thread newThread = new Thread(invoker);
        newThread.start();
        //refreshTimer.scheduleAtFixedRate(refreshTimer_Tick(), 0, 100);
    }

    private TimerTask refreshTimer_Tick() throws IOException {
        drawingPane.draw(drawingPane);
        return null;
    }

    @FXML
    public BorderPane borderPane;

    @FXML
    private MenuItem newOption;

    @FXML
    private MenuItem openOption;

    @FXML
    private MenuItem saveOption;

    @FXML
    private MenuItem undoOption;

    @FXML
    private MenuItem redoOption;

    @FXML
    private ToggleButton editBtn;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private ToggleButton newClassBtn;

    @FXML
    private ToggleButton binaryLineBtn;

    @FXML
    private ToggleButton generalizationLineBtn;

    @FXML
    private ToggleButton dependancyLineBtn;

    @FXML
    private ToggleButton aggregationLineBtn;

    @FXML
    private ToggleButton compositionLineBtn;

    @FXML
    void createNewFile_OnAction(ActionEvent event) {

    }

    @FXML
    void enterBinaryLineMode_OnAction(ActionEvent event) {

    }

    @FXML
    void enterEditMode_OnAction(ActionEvent event) {

    }

    @FXML
    void enterNewClassMode_OnAction(ActionEvent event) {

    }

    @FXML
    void openFile_OnAction(ActionEvent event) {

    }

    @FXML
    void redoCommand_OnAction(ActionEvent event) {

    }

    @FXML
    void saveFile_OnAction(ActionEvent event) {

    }

    @FXML
    void undoCommand_OnAction(ActionEvent event) {

    }

    @FXML
    public void drawingPane_OnMouseDown(MouseEvent event) {
        origPoint = new Point2D(event.getX(), event.getY());

        if (editBtn.isSelected()) {
            return;
        } else if (newClassBtn.isSelected()) {
            shapeGuide = new Rectangle(origPoint.getX(), origPoint.getY(), 0, 0);
            shapeGuide.setFill(Color.TRANSPARENT);
            shapeGuide.setStroke(Color.BLACK);
            shapeGuide.setStyle("-fx-stroke-dash-array: 20");
            drawingPane.getChildren().add(shapeGuide);
        } else {
            shapeGuide = new Line();
            shapeGuide.setStyle("-fx-stroke-dash-array: 20");
            drawingPane.getChildren().add(shapeGuide);
        }
    }

    @FXML
    public void drawingPane_OnMouseRelease(MouseEvent event) {
        drawingPane.getChildren().remove(shapeGuide);
        if (newClassBtn.isSelected()) {
            commandFactory.createAndDo("add_class", new Object[] {"newClass",  new Point2D(event.getX(), event.getY())});
        } else if (editBtn.isSelected()) {

        } else if (binaryLineBtn.isSelected()) {

        } else if (generalizationLineBtn.isSelected()) {

        } else if (dependancyLineBtn.isSelected()) {

        } else if (aggregationLineBtn.isSelected()) {

        } else if (compositionLineBtn.isSelected()) {

        }
    }

    public void drawingPane_OnMouseDragged(MouseEvent event) {
        double width = event.getX() - origPoint.getX();
        double height = event.getY() - origPoint.getY();
        Point2D size = new Point2D(event.getX() - origPoint.getX(), event.getY() - origPoint.getY());

        if (editBtn.isSelected()) {

        } else if (newClassBtn.isSelected()) {
            ((Rectangle) shapeGuide).setWidth(width > 0 ? width : -width);
            ((Rectangle) shapeGuide).setHeight(height > 0 ? height : -height);
            ((Rectangle) shapeGuide).setX(width > 0 ? origPoint.getX() : origPoint.getX() + width);
            ((Rectangle) shapeGuide).setY(height > 0 ? origPoint.getY() : origPoint.getY() + height);
        } else {
            ((Line) shapeGuide).setStartX(origPoint.getX());
            ((Line) shapeGuide).setStartY(origPoint.getY());
            ((Line) shapeGuide).setEndX(event.getX());
            ((Line) shapeGuide).setEndY(event.getY());
        }
    }
}
