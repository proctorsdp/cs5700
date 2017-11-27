package app.drawingComponents;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class LabeledBox extends Element {

    public LabeledBox() {}

    public LabeledBox(String label) {
        this.label.setText(label);
    }

    @FXML
    private StackPane labeledBoxPane;

    @FXML
    private TextField label;

    @FXML
    void moveBox(MouseEvent event) {
        labeledBoxPane.setTranslateX(event.getX());
        labeledBoxPane.setTranslateY(event.getY());
    }

    @FXML
    void saveLabel(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            label.commitValue();
        }
    }

    @Override
    public Element clone() {
        return null;
    }
}
