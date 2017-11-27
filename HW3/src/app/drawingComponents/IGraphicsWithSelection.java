package app.drawingComponents;

import com.sun.javafx.css.Size;
import javafx.geometry.Point2D;

public interface IGraphicsWithSelection {

    void drawSelectionBox(Point2D location, Size size);

    void drawSelectionLine(Point2D start, Point2D end);
}
