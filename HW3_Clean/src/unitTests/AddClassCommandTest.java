package unitTests;

import app.command.AddClassCommand;
import app.drawingComponents.ClassBox;
import app.drawingComponents.Drawing;
import app.drawingComponents.Element;
import app.drawingComponents.Size;
import gui.ToolBar;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.List;

//@RunWith( JfxTestRunner.class )
class AddClassCommandTest {

    private Drawing drawing;

    private AddClassCommand addClassCommand;

    private ClassBox classBox;

    @BeforeEach
    void setUp() {
        new JFXPanel();
        Platform.runLater(() -> {
            drawing = Drawing.getInstance();

            addClassCommand = new AddClassCommand(
                    new Object[] {
                            "test",
                            new Point2D(50,  50),
                            new Size(100, 100),
                            new ToolBar()});

            addClassCommand.setTargetDrawing(drawing);

            classBox = new ClassBox();
            classBox.setLocation(new Point2D(50, 50));
            classBox.setSize(new Size(100, 100));
        });
    }

    @AfterEach
    void tearDown() {
        drawing = null;
        addClassCommand = null;
        classBox = null;
    }

    @Test
    void execute() {
        new JFXPanel();
        Platform.runLater(() -> {
            addClassCommand.execute();
            Assert.assertTrue(drawing.getElements().contains(classBox));
        });
    }

    @Test
    void undo() {
        new JFXPanel();
        Platform.runLater(() -> {
            addClassCommand.execute();
            Assert.assertFalse(drawing.getElements().contains(classBox));
        });
    }

    @Test
    void redo() {
        new JFXPanel();
        Platform.runLater(() -> {
            addClassCommand.execute();
            Assert.assertTrue(drawing.getElements().contains(classBox));
        });
    }

}