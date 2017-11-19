package app.command;

import app.drawingComponents.Drawing;

public abstract class Command {

    public Drawing targetDrawing;

    public Drawing getTargetDrawing() {
        return targetDrawing;
    }

    public void setTargetDrawing(Drawing targetDrawing) {
        this.targetDrawing = targetDrawing;
    }

    public abstract boolean execute();

    public abstract void undo();

    public abstract void redo();
}
