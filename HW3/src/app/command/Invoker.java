package app.command;

import java.io.IOException;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Invoker implements Runnable {

    private Thread worker;
    private boolean keepGoing = true;

    private ConcurrentLinkedQueue<Command> toDoQueue = new ConcurrentLinkedQueue<>();
    private ManualResetEvent enqueueOccurred = new ManualResetEvent(false);

    private Stack<Command> undoStack = new Stack<Command>();
    private Stack<Command> redoStack = new Stack<Command>();



//    public void start() {
//        keepGoing = true;
//    }

    public void stop() {
        keepGoing = false;
    }

    public void enqueueCommandForExecution(Command cmd) {
        if (cmd != null) {
            toDoQueue.add(cmd);
            enqueueOccurred.set();
        }
    }

    public void undo() {
        enqueueCommandForExecution(new UndoCommand());
    }

    public void redo() {
        enqueueCommandForExecution(new RedoCommand());
    }

    public void run() {
        while (keepGoing) {
            Command cmd;
            if (!toDoQueue.isEmpty()) {
                cmd = toDoQueue.remove();
                if (cmd instanceof UndoCommand) {
                    executeUndo();
                } else if (cmd instanceof RedoCommand) {
                    executeRedo();
                } else {
                    try {
                        if (cmd.execute()) {
                            undoStack.push(cmd);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    enqueueOccurred.waitOne(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void executeUndo() {
        if (undoStack.size() <= 0) { return; }

        Command cmd = undoStack.pop();
        cmd.undo();
        redoStack.push(cmd);
    }

    private void executeRedo() {
        if (redoStack.size() <= 0) { return; }

        Command cmd = redoStack.pop();
        cmd.redo();
        undoStack.push(cmd);
    }
}
