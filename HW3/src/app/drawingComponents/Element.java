package app.drawingComponents;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import com.sun.prism.Graphics;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.Light;

public abstract class Element {

    public boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public abstract Element clone();

    public void draw(Graphics graphics) {}

    public boolean containsPoint(Point2D point) { return false; }

}
