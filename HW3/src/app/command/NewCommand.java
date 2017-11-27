package app.command;


import app.drawingComponents.Element;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.ArrayList;

public class NewCommand extends Command {
    
    private ObservableList<Node> previousElements;
    NewCommand(Object[] commandParameters) {}
    
    @Override
    public boolean execute() {
        previousElements = targetDrawing.getElements();
        targetDrawing.clear();
        return previousElements != null && previousElements.size() > 0;
    }

    @Override
    public void undo() {
        if (previousElements == null || previousElements.size() == 0) {
            return;
        }
        
        for (Node element : previousElements) {
            targetDrawing.addNode(element);
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
