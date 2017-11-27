package app.command;

import app.drawingComponents.Drawing;
import gui.DrawingPane;

import java.io.IOException;

public abstract class Command {

    public DrawingPane targetDrawing;

    public DrawingPane getTargetDrawing() {
        return targetDrawing;
    }

    public void setTargetDrawing(DrawingPane targetDrawing) {
        this.targetDrawing = targetDrawing;
    }

    public abstract boolean execute() throws IOException;

    public abstract void undo();

    public abstract void redo();
}
