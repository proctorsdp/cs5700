package unitTests;

import app.command.AddClassCommand;
import app.command.AddLineCommand;
import app.drawingComponents.ClassBox;
import app.drawingComponents.Drawing;
import app.drawingComponents.RelationshipLine;
import app.drawingComponents.Size;
import gui.ToolBar;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddLineCommandTest {

    private RelationshipLine testLine;

    private Drawing drawing;

    private AddLineCommand addLineCommand;

    @BeforeEach
    void setUp() {
        new JFXPanel();
        Platform.runLater(() -> {
            drawing = Drawing.getInstance();

            addLineCommand = new AddLineCommand(
                    new Object[] {
                            "test",
                            new ClassBox().getNode(),
                            new ClassBox().getNode(),
                            "Binary"});

            addLineCommand.setTargetDrawing(drawing);

            testLine = new RelationshipLine();
        });
    }

    @AfterEach
    void tearDown() {
        new JFXPanel();
        Platform.runLater(() -> {
            drawing = null;
            addLineCommand = null;
            testLine = null;
        });
    }

    @Test
    void execute() {
        new JFXPanel();
        Platform.runLater(() -> {
            addLineCommand.execute();
            Assert.assertTrue(drawing.getElements().contains(testLine));
        });
    }

    @Test
    void undo() {
        new JFXPanel();
        Platform.runLater(() -> {
            addLineCommand.undo();
            Assert.assertFalse(drawing.getElements().contains(testLine));
        });
    }

    @Test
    void redo() {
        new JFXPanel();
        Platform.runLater(() -> {
            addLineCommand.redo();
            Assert.assertTrue(drawing.getElements().contains(testLine));
        });
    }

}