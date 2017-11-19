package app.command;


import app.drawingComponents.Element;
import javafx.scene.Node;

import java.util.ArrayList;

public class NewCommand extends Command {
    
    private ArrayList<Element> previousElements;
    NewCommand(Object[] commandParameters) {}
    
    @Override
    public boolean execute() {
        previousElements = new ArrayList<>();
        previousElements.addAll(targetDrawing.getElements());
        targetDrawing.clear();
        return previousElements != null && previousElements.size() > 0;
    }

    @Override
    public void undo() {
        if (previousElements == null || previousElements.size() == 0) {
            return;
        }
        
        for (Element element : previousElements) {
            targetDrawing.addNodeToFront(element);
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
