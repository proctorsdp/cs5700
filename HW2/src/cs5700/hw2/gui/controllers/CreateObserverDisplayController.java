package cs5700.hw2.gui.controllers;

import cs5700.hw2.application.tools.ObserverTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateObserverDisplayController {
    private static ObserverTypes newObsever;

    public ObserverTypes getNewObserver() {
        return newObsever;
    }

    @FXML
    private TitledPane createObserverTitledPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private RadioButton emailObserverRadio;

    @FXML
    private RadioButton progressObserverRadio;

    @FXML
    private RadioButton comparisonObserverRadio;

    @FXML
    private RadioButton leaderBoardObserverRadio;

    @FXML
    private Button confirmButton;


    void showStage() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/cs5700/hw2/gui/fxml/createObserverDisplay.fxml"));
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void createObserver(ActionEvent actionEvent) {
        if (emailObserverRadio.isSelected()) {
            newObsever = ObserverTypes.EMAIL;
        } else if (progressObserverRadio.isSelected()) {
            newObsever = ObserverTypes.PROGRESS;
        } else if (comparisonObserverRadio.isSelected()) {
            newObsever = ObserverTypes.COMPARE;
        } else if (leaderBoardObserverRadio.isSelected()) {
            newObsever = ObserverTypes.LEADERBOARD;
        }

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
