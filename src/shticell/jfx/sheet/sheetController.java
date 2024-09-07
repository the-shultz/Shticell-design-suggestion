package shticell.jfx.sheet;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class sheetController {

    @FXML
    private ComboBox<String> alignmentBox;

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

    @FXML
    private CheckBox markCheckbox;

    @FXML
    private ComboBox<String> selectCellForEditCombobox;

    @FXML
    private TextField cellInputContentTextfield;

    @FXML
    private Spinner<Integer> heightPicker;

    @FXML
    private CheckBox toggleColorCheckbox;

    @FXML
    private Spinner<Integer> widthPicker;

    private ObjectProperty<Label> selectedCell;

    private UIModel uiModel;

    @FXML
    private void initialize() {

        // update cell data through the ui model
        uiModel = new UIModel(cellA1, cellA2, cellB1, cellB2);

        ObservableList<String> cells =
                FXCollections.observableArrayList(
                        "A1", "B1", "A2", "b2"
                );
        selectCellForEditCombobox.setItems(cells);
        selectCellForEditCombobox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            cellInputContentTextfield.clear();
        });

        cellInputContentTextfield.textProperty().addListener((o, oldValue, newValue) -> {
            switch (selectCellForEditCombobox.getSelectionModel().getSelectedIndex()) {
                case 0:
                    uiModel.cellA1Property().set(newValue);
                    break;
                case 1:
                    uiModel.cellB1Property().set(newValue);
                    break;
                case 2:
                    uiModel.cellA2Property().set(newValue);
                    break;
                case 3:
                    uiModel.cellB2Property().set(newValue);
                    break;
            }
        });

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Left",
                        "Center",
                        "Right"
                );
        alignmentBox.setItems(options);

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

        // column width picker
        widthPicker
                .valueProperty()
                .addListener((observable, oldValue, newValue) -> changeSecondColumnWidth(newValue));

        SpinnerValueFactory<Integer> widthValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 200, 100, 1);
        widthPicker.setValueFactory(widthValueFactory);

        // row height picker
        heightPicker
                .valueProperty()
                .addListener((observable, oldValue, newValue) -> changeSecondRowWidth(newValue));

        SpinnerValueFactory<Integer> heightValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(30, 100, 40, 1);
        heightPicker.setValueFactory(heightValueFactory);

        // set initial values
        Platform.runLater(() -> {
            alignmentBox.getSelectionModel().selectFirst();

            widthPicker.getValueFactory().setValue(100);

            heightPicker.getValueFactory().setValue(40);
        });
    }

    @FXML
    void alignmentSelectionListener(ActionEvent event) {
        int selectedIndex = alignmentBox.getSelectionModel().getSelectedIndex();
        switch (selectedIndex) {
            case 0:
                cellA1.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                cellA2.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                break;
            case 1:
                cellA1.setAlignment(javafx.geometry.Pos.CENTER);
                cellA2.setAlignment(javafx.geometry.Pos.CENTER);
                break;
            case 2:
                cellA1.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
                cellA2.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
                break;
        }
    }

    @FXML
    void markCellsButtonActionListener(ActionEvent event) {
        if (markCheckbox.isSelected()) {
            cellA1.getStyleClass().add("depends-on-cell");
            cellB1.getStyleClass().add("depends-on-cell");
            cellB2.getStyleClass().add("influence-on-cell");
        } else {
            cellA1.getStyleClass().remove("depends-on-cell");
            cellB1.getStyleClass().remove("depends-on-cell");
            cellB2.getStyleClass().remove("influence-on-cell");
        }
    }

    @FXML
    void toggleCellColor(ActionEvent event) {
        if (toggleColorCheckbox.isSelected()) {
            cellA1.getStyleClass().remove("background-cell");
//            cellA1.setStyle("-fx-background-color: red;"); // not working from some reason due to collision with css
            cellA1.setBackground(Background.fill(javafx.scene.paint.Color.RED));
            cellA1.setStyle("-fx-text-fill: white;");
        } else {
            cellA1.getStyleClass().add("background-cell");
            cellA1.setStyle("-fx-text-fill: black;");
        }
    }


    @FXML
    void inputCellActionListener(ActionEvent event) {
        System.out.println("tf action field: " + cellInputContentTextfield.getText());
    }

    private void changeSecondColumnWidth(double width) {
        ColumnConstraints columnConstraints = masterGridPane.getColumnConstraints().get(3);
        columnConstraints.setMinWidth(width);
        columnConstraints.setPrefWidth(width);
        columnConstraints.setMaxWidth(width);
    }

    private void changeSecondRowWidth(double width) {
        RowConstraints rowConstraints = masterGridPane.getRowConstraints().get(2);
        rowConstraints.setMinHeight(width);
        rowConstraints.setPrefHeight(width);
        rowConstraints.setMaxHeight(width);
    }

    private void addClickEventForCell(Label label) {
        label.setOnMouseClicked(event -> {
            selectedCell.set(label);
        });
    }
}