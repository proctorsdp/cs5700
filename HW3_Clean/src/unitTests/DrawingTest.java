package unitTests;

import app.drawingComponents.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;



class DrawingTest {

    private Drawing drawing = Drawing.getInstance();

    private RelationshipLine testLine;

    private ClassBox testClassBox;

    @BeforeEach
    void setUp() {
        new JFXPanel();
        Platform.runLater(() -> {
            ArrayList<Element> elements = new ArrayList<>();
            elements.add(new ClassBox());
            elements.add(new ClassBox());
            elements.add(new RelationshipLine());
            elements.add(new ClassBox());
            elements.add(new RelationshipLine());

            drawing.setElements(elements);

            testLine = new RelationshipLine();
            testLine.setDescription("test");

            testClassBox = new ClassBox();
            testClassBox.setDescription("test");
        });
    }

    @AfterEach
    void tearDown() {
        drawing.clear();
        testLine = null;
        testClassBox = null;
    }

    @Test
    void addNodeToFront() {
        new JFXPanel();
        Platform.runLater(() -> {
            drawing.addNodeToFront(testLine);
            Assert.assertEquals(drawing.getElements().get(drawing.size()-1), testLine);


            drawing.addNodeToFront(testClassBox);
            Assert.assertEquals(drawing.getElements().get(drawing.size()-1), testLine);
        });
    }

    @Test
    void addNodeToBack() {
        new JFXPanel();
        Platform.runLater(() -> {
            drawing.addNodeToBack(testLine);
            Assert.assertEquals(drawing.getElements().get(0), testLine);


            drawing.addNodeToBack(testClassBox);
            Assert.assertEquals(drawing.getElements().get(0), testLine);
        });
    }

    @Test
    void deleteNode() {
        new JFXPanel();
        Platform.runLater(() -> {
            drawing.add(testLine);
            drawing.deleteNode(testLine);
            Assert.assertFalse(drawing.contains(testLine));


            drawing.add(testClassBox);
            drawing.deleteNode(testClassBox);
            Assert.assertFalse(drawing.contains(testClassBox));
        });
    }

    @Test
    void deleteRelationships() {
        new JFXPanel();
        Platform.runLater(() -> {
            ClassBox classOne = new ClassBox();
            ClassBox classTwo = new ClassBox();
            testLine.bindToNodes(classOne.getNode(), classTwo.getNode());
            drawing.addNodeToFront(classOne);
            drawing.addNodeToFront(classTwo);
            drawing.addNodeToBack(testLine);

            drawing.deleteRelationships(classOne.getNode());

            Assert.assertTrue(drawing.contains(classTwo));
            Assert.assertFalse(drawing.contains(classOne));
            Assert.assertFalse(drawing.contains(testLine));
        });
    }

    @Test
    void findElementAtPosition() {
        new JFXPanel();
        Platform.runLater(() -> {
            testClassBox.setSize(new Size(100, 100));
            testClassBox.setLocation(new Location(50, 50));
            drawing.addNodeToFront(testClassBox);

            Node node = drawing.findElementAtPosition(new Point2D(75, 75));
            Assert.assertFalse(node == null);
        });
    }

    @Test
    void draw() {
        new JFXPanel();
        Platform.runLater(() -> {
            Pane testPane = new Pane();
            drawing.draw(testPane);

            for (Element element : drawing) {
                Assert.assertTrue(testPane.getChildren().contains(element.getNode()));
            }
        });
    }



}