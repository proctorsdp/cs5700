package app.command;

import app.drawingComponents.Element;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Line;

public class AddLineCommand extends Command {

    private Point2D start;
    private Point2D end;
    private Node line;

    void addLineCommand(Object[] commandParameters) {
        if (commandParameters.length > 0) {
            start = (Point2D) commandParameters[0];
        }

        if (commandParameters.length > 1) {
            end = (Point2D) commandParameters[1];
        }
    }
    @Override
    public boolean execute() {
        if (start == null || end == null) {
            return false;
        }

        //line = new Line();
        targetDrawing.addNode(line);

        return true;
    }

    @Override
    public void undo() {
        targetDrawing.deleteNode(line);
    }

    @Override
    public void redo() {
        targetDrawing.addNode(line);
    }
}
