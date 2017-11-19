package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class ToolBar {

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

    public void setEditBtnAction(EventHandler<ActionEvent> event) { editBtn.setOnAction(event); }

    public void setNewClassBtnAction(EventHandler<ActionEvent> event) { newClassBtn.setOnAction(event); }

    public void setBinaryLineBtnAction(EventHandler<ActionEvent> event) { binaryLineBtn.setOnAction(event); }

    public void setGeneralizationLineBtnAction(EventHandler<ActionEvent> event) { generalizationLineBtn.setOnAction(event); }

    public void setDependancyLineBtnAction(EventHandler<ActionEvent> event) { dependancyLineBtn.setOnAction(event); }

    public void setAggregationLineBtnAction(EventHandler<ActionEvent> event) { aggregationLineBtn.setOnAction(event); }

    public void setCompositionLineBtnAction(EventHandler<ActionEvent> event) { compositionLineBtn.setOnAction(event); }

    public boolean isEditBtnSelected() { return editBtn.isSelected(); }

}
