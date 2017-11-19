package app.drawingComponents;

import javafx.geometry.Bounds;
import javafx.scene.Node;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlTransient
@XmlSeeAlso({ClassBox.class, RelationshipLine.class})
public abstract class Element implements Serializable {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract Bounds getBounds();

    public abstract Node getNode();

    public abstract double getX();

    public abstract double getY();

}
