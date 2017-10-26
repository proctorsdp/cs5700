package cs5700.hw2.gui.controllers;

import cs5700.hw2.application.observers.EmailUpdates;
import cs5700.hw2.application.observers.IAthleteObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmailIDisplayController implements IDisplayController {
    private EmailUpdates observer;

    @FXML
    public Label choiceBoxLabel;

    @FXML
    public TextField delayTextBox;

    @FXML
    private TitledPane emailTitledPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField emailTextBox;

    @FXML
    private Label emailBoxLabel;

    @FXML
    private ChoiceBox<Integer> delayChoiceBox;

    @FXML
    private Button enterButton;

    @FXML
    public void buttonClicked(ActionEvent actionEvent) {
        if (!emailTextBox.getText().isEmpty() && !delayTextBox.getText().isEmpty()) {
            saveEmail();
        }
    }

    @FXML
    public void enterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            saveEmail();
        }
    }

    private void saveEmail() {
        String email = emailTextBox.getText();
        int delay = Integer.parseInt(delayTextBox.getText());
        observer = new EmailUpdates(email, delay);

        Stage stage = (Stage) enterButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public IAthleteObserver getObserver() {
        return observer;
    }

    @Override
    public void execute() {

    }
}
