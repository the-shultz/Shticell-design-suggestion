package shticell.jfx.sheet;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SheetControllerImpl implements SheetController {

    @FXML
    private Label cellA1;

    @FXML
    private Label cellA2;

    @FXML
    private Label cellB1;

    @FXML
    private Label cellB2;

    @FXML
    private GridPane masterGridPane;

    private ObjectProperty<Label> selectedCell;

    private UIModel uiModel;

    @FXML
    private void initialize() {

        // update cell data through the ui model
        uiModel = new UIModel(cellA1, cellA2, cellB1, cellB2);

        // selected cell listener
        selectedCell = new SimpleObjectProperty<>();
        selectedCell.addListener((observableValue, oldLabelSelection, newSelectedLabel) -> {
            if (oldLabelSelection != null) {
                oldLabelSelection.setId(null);
            }
            if (newSelectedLabel != null) {
                newSelectedLabel.setId("selected-cell");
            }
        });
        addClickEventForCell(cellA1);
        addClickEventForCell(cellA2);
        addClickEventForCell(cellB1);
        addClickEventForCell(cellB2);
    }

    @Override
    public void alignCells(javafx.geometry.Pos alignment) {
        cellA1.setAlignment(alignment);
        cellA2.setAlignment(alignment);
    }

    @Override
    public void markCellsButtonActionListener(boolean isMarked) {
        if (isMarked) {
            cellA1.getStyleClass().add("depends-on-cell");
            cellB1.getStyleClass().add("depends-on-cell");
            cellB2.getStyleClass().add("influence-on-cell");
        } else {
            cellA1.getStyleClass().remove("depends-on-cell");
            cellB1.getStyleClass().remove("depends-on-cell");
            cellB2.getStyleClass().remove("influence-on-cell");
        }
    }

    @Override
    public void toggleCellColor(boolean isSelected) {
        if (isSelected) {
            cellA1.getStyleClass().remove("background-cell");
//            cellA1.setStyle("-fx-background-color: red;"); // not working from some reason due to collision with css
            cellA1.setBackground(Background.fill(javafx.scene.paint.Color.RED));
            cellA1.setStyle("-fx-text-fill: white;");
        } else {
            cellA1.getStyleClass().add("background-cell");
            cellA1.setStyle("-fx-text-fill: black;");
        }
    }

    @Override
    public void changeSecondColumnWidth(double width) {
        ColumnConstraints columnConstraints = masterGridPane.getColumnConstraints().get(3);
        columnConstraints.setMinWidth(width);
        columnConstraints.setPrefWidth(width);
        columnConstraints.setMaxWidth(width);
    }

    @Override
    public void changeSecondRowWidth(double width) {
        RowConstraints rowConstraints = masterGridPane.getRowConstraints().get(2);
        rowConstraints.setMinHeight(width);
        rowConstraints.setPrefHeight(width);
        rowConstraints.setMaxHeight(width);
    }

    @Override
    public void updateCellContent(String cellId, String content) {
        switch (cellId) {
            case "A1":
                uiModel.cellA1Property().set(content);
                break;
            case "B1":
                uiModel.cellB1Property().set(content);
                break;
            case "A2":
                uiModel.cellA2Property().set(content);
                break;
            case "B2":
                uiModel.cellB2Property().set(content);
                break;
        }
    }

    private void addClickEventForCell(Label label) {
        label.setOnMouseClicked(event -> {
            selectedCell.set(label);
        });
    }
}