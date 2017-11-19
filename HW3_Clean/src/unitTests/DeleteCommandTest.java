package unitTests;

import app.command.DeleteCommand;
import app.drawingComponents.ClassBox;
import app.drawingComponents.Drawing;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {

    private Drawing drawing;

    private ClassBox classBox;

    private DeleteCommand deleteCommand;

    @BeforeEach
    void setUp() {
        new JFXPanel();
        Platform.runLater(() -> {
            drawing = Drawing.getInstance();
            classBox = new ClassBox();
            deleteCommand = new DeleteCommand(
                    new Object[] {
                            "test",
                            classBox.getNode()
                    }
            );
        });
    }

    @AfterEach
    void tearDown() {
        new JFXPanel();
        Platform.runLater(() -> {
            classBox = null;
            deleteCommand = null;
        });
    }

    @Test
    void execute() {
        new JFXPanel();
        Platform.runLater(() -> {
            deleteCommand.execute();
            Assert.assertFalse(drawing.contains(classBox));
        });
    }

    @Test
    void undo() {
        new JFXPanel();
        Platform.runLater(() -> {
            deleteCommand.execute();
            Assert.assertTrue(drawing.contains(classBox));
        });
    }

    @Test
    void redo() {
        new JFXPanel();
        Platform.runLater(() -> {
            deleteCommand.execute();
            Assert.assertFalse(drawing.contains(classBox));
        });
    }

}