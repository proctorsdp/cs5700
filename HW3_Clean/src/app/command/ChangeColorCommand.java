package app.command;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ChangeColorCommand extends Command {

    private String label;

    private Node node;

    private Background newFill;

    private Background oldFill;


    ChangeColorCommand(Object[] commandParameters) {
        if (commandParameters.length > 0) {
            label = (String) commandParameters[0];
        }

        if (commandParameters.length > 1) {
            node = (Node) commandParameters[1];
            Color oldColor = (Color) ((TextField) ((VBox) node).getChildren().get(0)).getBackground().getFills().get(0).getFill();
            oldFill = new Background(new BackgroundFill(oldColor, new CornerRadii(0), new Insets(0)));
        }

        if (commandParameters.length > 2) {
            Color newColor = (Color) commandParameters[2];
            newFill = new Background(new BackgroundFill(newColor, new CornerRadii(0), new Insets(0)));
        }
    }

    @Override
    public boolean execute() {
        ((TextField) ((VBox) node).getChildren().get(0)).setBackground(newFill);
        ((Region) ((VBox) node).getChildren().get(1)).setBackground(newFill);
        return true;
    }

    @Override
    public void undo() {
        ((TextField) ((VBox) node).getChildren().get(0)).setBackground(oldFill);
        ((Region) ((VBox) node).getChildren().get(1)).setBackground(oldFill);
    }

    @Override
    public void redo() {
        ((TextField) ((VBox) node).getChildren().get(0)).setBackground(newFill);
        ((Region) ((VBox) node).getChildren().get(1)).setBackground(newFill);
    }
}
