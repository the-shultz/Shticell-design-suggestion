package shticell.jfx.sheet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

public class UIModel {

    private final StringProperty cellA1;
    private final StringProperty cellA2;
    private final StringProperty cellB1;
    private final StringProperty cellB2;

    public UIModel(Label CellA1Label, Label CellA2Label, Label CellB1Label, Label CellB2Label) {
        cellA1 = new SimpleStringProperty();
        cellA2 = new SimpleStringProperty();
        cellB1 = new SimpleStringProperty();
        cellB2 = new SimpleStringProperty();

        CellA1Label.textProperty().bind(cellA1);
        CellA2Label.textProperty().bind(cellA2);
        CellB1Label.textProperty().bind(cellB1);
        CellB2Label.textProperty().bind(cellB2);

    }

    public StringProperty cellA1Property() {
        return cellA1;
    }

    public StringProperty cellA2Property() {
        return cellA2;
    }

    public StringProperty cellB1Property() {
        return cellB1;
    }

    public StringProperty cellB2Property() {
        return cellB2;
    }
}
