package gui;

import app.command.CommandFactory;
import app.invoker.Invoker;
import app.drawingComponents.Drawing;
import app.drawingComponents.Element;
import app.drawingComponents.Size;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;


public class Controller extends BorderPane {

    private CommandFactory commandFactory;

    private Invoker invoker;

    private MenuBar menuBar;

    private ToolBar toolBar;

    private Pane drawingPane;

    private Drawing drawing;

    private Point2D origPoint;

    private Point2D finalPoint;

    private Point2D origTranslate;

    private Point2D finalTranslate;

    private boolean validCopy = false;

    private Node startNode;

    private Node endNode;

    private Node selectedElement;

    private Shape shapeGuide;

    private String fileName;

    private drawingMode mode = drawingMode.NEW_CLASS;

    private drawingLine line;

    private FileChooser fileChooser;

    private enum drawingMode {
        EDIT,
        NEW_CLASS,
        LINE
    }

    private enum drawingLine {
        BINARY,
        GENERAL,
        DEPENDENCY,
        AGGREGATION,
        COMPOSITION
    }

    Controller(FXMLLoader menuBar, FXMLLoader toolBar, Pane drawingPane) throws IOException {
        this.setTop(menuBar.load());
        this.setLeft(toolBar.load());
        this.setCenter(drawingPane);

        this.menuBar = menuBar.getController();
        this.toolBar = toolBar.getController();
        this.drawingPane = drawingPane;
        this.drawing = Drawing.getInstance();
        this.commandFactory = CommandFactory.getInstance();
        this.invoker = new Invoker();

        initMenuBar();
        initToolBar();
        initDrawingPane();
        initFileChooser();

        commandFactory.setTargetDrawing(drawing);
        commandFactory.setInvoker(invoker);

        Thread newThread = new Thread(invoker, "Invoker_Thread");
        newThread.start();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drawing.draw(drawingPane);
            }
        }, 0, 300);
    }

    public void initMenuBar() {
        menuBar.setNewOptionAction(this::callNew);
        menuBar.setOpenOptionAction(this::callOpen);
        menuBar.setSaveOptionAction(this::callSave);
        menuBar.setSaveAsOptionAction(this::callSaveAs);
        menuBar.setUndoOptionAction(this::callUndo);
        menuBar.setRedoOptionAction(this::callRedo);
        menuBar.setCopyOptionAction(this::copyClass);
        menuBar.setPasteOptionAction(this::pasteClass);
        menuBar.setColorOptionAction(this::changeColor);
        menuBar.setDeleteOptionAction(this::deleteSelectedNode);
        menuBar.setClassEditable(false);
        menuBar.setSaveOptionDisable(true);
    }

    public void initToolBar() {
        toolBar.setEditBtnAction(event -> setMode(drawingMode.EDIT, null));
        toolBar.setNewClassBtnAction(event -> setMode(drawingMode.NEW_CLASS, null));
        toolBar.setBinaryLineBtnAction(event -> setMode(drawingMode.LINE, drawingLine.BINARY));
        toolBar.setGeneralizationLineBtnAction(event -> setMode(drawingMode.LINE, drawingLine.GENERAL));
        toolBar.setDependancyLineBtnAction(event -> setMode(drawingMode.LINE, drawingLine.DEPENDENCY));
        toolBar.setAggregationLineBtnAction(event -> setMode(drawingMode.LINE, drawingLine.AGGREGATION));
        toolBar.setCompositionLineBtnAction(event -> setMode(drawingMode.LINE, drawingLine.COMPOSITION));
    }

    private void setMode(drawingMode newMode, drawingLine newLine) {
        mode = newMode;
        line = newLine;
    }

    private void initDrawingPane() {
        drawingPane.setOnMouseDragged(this::drawingPane_OnMouseDragged);
        drawingPane.setOnMousePressed(this::drawingPane_OnMousePressed);
        drawingPane.setOnMouseReleased(this::drawingPane_OnMouseReleased);
    }

    private void initFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".xml", "*.xml"));
    }

    public void drawingPane_OnMousePressed(MouseEvent event) {
        origPoint = new Point2D(event.getX(), event.getY());

        switch (mode) {
            case EDIT:
                selectedElement = drawing.findElementAtPosition(origPoint);
                if (selectedElement != null) {
                    menuBar.setClassEditable(mode == drawingMode.EDIT);
                    origTranslate = new Point2D(selectedElement.getTranslateX(), selectedElement.getTranslateY());
                }
                break;
            case NEW_CLASS:
                shapeGuide = new Rectangle(origPoint.getX(), origPoint.getY(), 0, 0);
                shapeGuide.setFill(Color.TRANSPARENT);
                shapeGuide.setStroke(Color.BLACK);
                shapeGuide.setStyle("-fx-stroke-dash-array: 20");
                drawingPane.getChildren().add(shapeGuide);
                break;
            case LINE:
                shapeGuide = new Line();
                shapeGuide.setStyle("-fx-stroke-dash-array: 20");
                drawingPane.getChildren().add(shapeGuide);
                break;
        }
    }

    public void drawingPane_OnMouseDragged(MouseEvent event) {
        double width = event.getX() - origPoint.getX();
        double height = event.getY() - origPoint.getY();
//        Point2D size = new Point2D(event.getX() - origPoint.getX(), event.getY() - origPoint.getY());

        switch (mode) {
            case EDIT:
                finalTranslate = new Point2D(selectedElement.getTranslateX(), selectedElement.getTranslateY());
                break;
            case NEW_CLASS:
                ((Rectangle) shapeGuide).setWidth(width > 0 ? width : -width);
                ((Rectangle) shapeGuide).setHeight(height > 0 ? height : -height);
                ((Rectangle) shapeGuide).setX(width > 0 ? origPoint.getX() : origPoint.getX() + width);
                ((Rectangle) shapeGuide).setY(height > 0 ? origPoint.getY() : origPoint.getY() + height);
                break;
            case LINE:
                ((Line) shapeGuide).setStartX(origPoint.getX());
                ((Line) shapeGuide).setStartY(origPoint.getY());
                ((Line) shapeGuide).setEndX(event.getX());
                ((Line) shapeGuide).setEndY(event.getY());
                break;
        }
    }

    public void drawingPane_OnMouseReleased(MouseEvent event) {
        drawingPane.getChildren().remove(shapeGuide);
        finalPoint = new Point2D(event.getX(), event.getY());
        Size size = new Size(finalPoint.getX()-origPoint.getX(), finalPoint.getY()-origPoint.getY());
        switch (mode) {
            case EDIT:
                if (selectedElement != null && !origPoint.equals(finalPoint)) {
                    commandFactory.createAndDo("move_object", new Object[]{"moveObject", origTranslate, finalTranslate, selectedElement});
                    selectedElement = null;
                }
                break;
            case NEW_CLASS:
                commandFactory.createAndDo("new_class", new Object[] {"Class", origPoint, size, toolBar});
                break;
            case LINE:
                if (lineConnectsObjects()) {
                    createLine();
                }
                break;
        }
    }

    public void deleteSelectedNode(ActionEvent event) {
        if (selectedElement == null) { return; }
        commandFactory.createAndDo("delete_object", new Object[] {"deleteObject", selectedElement});
        selectedElement = null;
    }

    private void createLine() {
        String lineType = "";
        switch (line) {
            case BINARY:
                lineType = "binary";
                break;
            case GENERAL:
                lineType = "general";
                break;
            case DEPENDENCY:
                lineType = "dependency";
                break;
            case AGGREGATION:
                lineType = "aggregation";
                break;
            case COMPOSITION:
                lineType = "composition";
                break;
        }
        if (!lineType.equals("")) {
            commandFactory.createAndDo("new_line", new Object[]{"newLine", startNode, endNode, lineType});
        }
    }

    private void callUndo(ActionEvent event) {
        invoker.undo();
    }

    private void callRedo(ActionEvent event) {
        invoker.redo();
    }

    private void copyClass(ActionEvent event) {
        if (selectedElement == null) {return;}
        validCopy = true;
    }

    private void pasteClass(ActionEvent event) {
        if (!validCopy) { return; }
        Point2D newPoint = new Point2D(selectedElement.getTranslateX() + 50, selectedElement.getTranslateY() + 50);
        Size size = new Size(selectedElement.getBoundsInParent().getWidth(), selectedElement.getBoundsInParent().getHeight());
        commandFactory.createAndDo("new_class", new Object[] {"ClassCopy", newPoint, size, toolBar});
        validCopy = false;
    }

    private void changeColor(ActionEvent event) {
        if (selectedElement == null) { return; }
        ColorPicker colorPicker = new ColorPicker();
        Stage colorStage = new Stage();
        colorStage.setScene(new Scene(colorPicker));
        colorStage.showAndWait();
        commandFactory.createAndDo("change_color", new Object[] {"newColor", selectedElement, colorPicker.getValue()});
        selectedElement = null;
    }

    private boolean lineConnectsObjects() {
        boolean obj1 = false;
        boolean obj2 = false;
        for (Element n : drawing.getElements()) {
            if (n.getBounds().contains(origPoint)) {
                obj1 = true;
                startNode = n.getNode();
            } else if (n.getBounds().contains(finalPoint)) {
                obj2 = true;
                endNode = n.getNode();
            }
        }
        return obj1 && obj2;
    }

    private void callSaveAs(ActionEvent event) {
        File newFile = fileChooser.showSaveDialog(new Stage());
        if (newFile == null) { return; }
        fileName = newFile.getAbsolutePath();
        callSave(event);
        menuBar.setSaveOptionDisable(false);
    }

    private void callSave(ActionEvent event) {
        try {
            drawing.saveToStream(fileName);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void callOpen(ActionEvent event) {
        if (!drawing.isEmpty()) {
            Optional<ButtonType> result = saveWarning("Continue without saving?");
            if (!result.isPresent() || result.get() == ButtonType.NO) {
                return;
            }
        }
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile == null) { return; }
        fileName = selectedFile.getAbsolutePath();
        try {
            drawing.loadFromStream(fileName, toolBar);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        menuBar.setSaveOptionDisable(false);
    }

    private Optional<ButtonType> saveWarning(String warning) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setGraphic(null);
        alert.setHeaderText("Warning");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        alert.setContentText(warning + "\nAll unsaved data will be lost.");
        alert.setTitle("Save Project");
        return alert.showAndWait();
    }

    private void callNew(ActionEvent event) {
        if (!drawing.isEmpty()) {
            Optional<ButtonType> result = saveWarning("Do you want to save the current project?");
            if (!result.isPresent()) {
                return;
            }
            if (result.get() == ButtonType.YES) {
                callSave(event);
            }
        }
        drawing.clear();
        fileName = "";
        menuBar.setSaveOptionDisable(true);
    }
}
