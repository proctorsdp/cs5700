package cs5700.hw2.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class RaceInfoDisplayController {
    private String courseName = "Untitled";
    private String eventName = "Untitled";
    private LocalDate raceDate;
    private String fileName;
    private double raceLength;


    public String getCourseName() {
        return courseName;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getRaceDate() {
        return raceDate;
    }

    public String getFileName() {
        return fileName;
    }

    public double getRaceLength() {
        return raceLength;
    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField courseNameTextBox;

    @FXML
    private Label courseNameLabel;

    @FXML
    private RadioButton tenMileRadioButton;

    @FXML
    private RadioButton hundredMileRadioButton;

    @FXML
    private TextField eventTitleTextBox;

    @FXML
    private Label eventLabel;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label datePickerLabel;

    @FXML
    private Button confirmButton;


    void showStage() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/cs5700/hw2/gui/fxml/raceInfoDisplay.fxml"));
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void saveRaceInfo(ActionEvent actionEvent) {

        if (tenMileRadioButton.isSelected()) {
            fileName = "data/Short Race Simulation-01.csv";
            raceLength = 10.0;
        } else if (hundredMileRadioButton.isSelected()) {
            fileName = "data/Century Simulation-01.csv";
            raceLength = 100.0;
        } else {
            return;
        }

        if (!courseNameTextBox.getText().isEmpty()) {
            courseName = courseNameTextBox.getText();
        }

        if (!eventTitleTextBox.getText().isEmpty()) {
            eventName = eventTitleTextBox.getText();
        }

        if (datePicker.getValue() != null) {
            raceDate = datePicker.getValue();
        }
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public void toggleHundred(ActionEvent actionEvent) {
        if (tenMileRadioButton.isSelected()) {
            tenMileRadioButton.setSelected(false);
        }
    }

    public void toggleTen(ActionEvent actionEvent) {
        if (hundredMileRadioButton.isSelected()) {
            hundredMileRadioButton.setSelected(false);
        }
    }
}
