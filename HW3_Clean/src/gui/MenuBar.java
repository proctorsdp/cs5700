package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuBar {

    @FXML
    public MenuItem deleteOption;

    @FXML
    public MenuItem saveAsOption;

    @FXML
    private MenuItem copyOption;

    @FXML
    private MenuItem pasteOption;

    @FXML
    private MenuItem colorOption;

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

    public void setNewOptionAction(EventHandler<ActionEvent> event) { newOption.setOnAction(event); }

    public void setOpenOptionAction(EventHandler<ActionEvent> event) { openOption.setOnAction(event); }

    public void setSaveOptionAction(EventHandler<ActionEvent> event) { saveOption.setOnAction(event); }

    public void setUndoOptionAction(EventHandler<ActionEvent> event) { undoOption.setOnAction(event); }

    public void setRedoOptionAction(EventHandler<ActionEvent> event) { redoOption.setOnAction(event); }

    public void setCopyOptionAction(EventHandler<ActionEvent> event) { copyOption.setOnAction(event); }

    public void setPasteOptionAction(EventHandler<ActionEvent> event) { pasteOption.setOnAction(event); }

    public void setColorOptionAction(EventHandler<ActionEvent> event) { colorOption.setOnAction(event); }

    public void setDeleteOptionAction(EventHandler<ActionEvent> event) { deleteOption.setOnAction(event); }

    public void setSaveAsOptionAction(EventHandler<ActionEvent> event) { saveAsOption.setOnAction(event); }

    public void setSaveOptionDisable(boolean disable) { saveOption.setDisable(disable); }

    public void setClassEditable(boolean editable) {
        deleteOption.setDisable(!editable);
        copyOption.setDisable(!editable);
        pasteOption.setDisable(!editable);
        colorOption.setDisable(!editable);
    }
}