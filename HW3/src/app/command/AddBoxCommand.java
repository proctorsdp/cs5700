package app.command;

import app.drawingComponents.Element;
import app.drawingComponents.LabeledBox;
import com.sun.javafx.css.Size;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.io.IOException;

public class AddBoxCommand extends Command {

    private Point2D location;
    private String label;
    private Node labeledBox;

    AddBoxCommand(Object[] commandParameters) {
        if (commandParameters.length > 0) {
            label = (String) commandParameters[0];
        }

        if (commandParameters.length > 1) {
            location = (Point2D) commandParameters[1];
        }

//        if (commandParameters.length > 2) {
//            size = (Size) commandParameters[2];
//        }
    }

    @Override
    public boolean execute() throws IOException {

        labeledBox = FXMLLoader.load(getClass().getResource("/app/drawingComponents/LabeledBox.fxml"));
        targetDrawing.addNode(labeledBox);

        return true;
    }

    @Override
    public void undo() {
        targetDrawing.deleteNode(labeledBox);
    }

    @Override
    public void redo() {
        targetDrawing.addNode(labeledBox);
    }
}
