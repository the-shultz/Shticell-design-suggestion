package shticell.jfx.main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import shticell.jfx.sheet.SheetController;

public class MainController {

    @FXML private GridPane sheetComponent;

    @FXML private SheetController sheetComponentController;

    @FXML
    private ComboBox<String> alignmentBox;

    @FXML
    private TextField cellInputContentTextfield;

    @FXML
    private Spinner<Integer> heightPicker;

    @FXML
    private CheckBox markCheckbox;

    @FXML
    private ComboBox<String> selectCellForEditCombobox;

    @FXML
    private CheckBox toggleColorCheckbox;

    @FXML
    private Spinner<Integer> widthPicker;

    @FXML
    private void initialize() {

        // selected cell stuff
        selectCellForEditCombobox.setItems(FXCollections.observableArrayList("A1", "B1", "A2", "B2" ));
        selectCellForEditCombobox
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) ->
                        cellInputContentTextfield.clear());

        cellInputContentTextfield
                .textProperty()
                .addListener((o, oldValue, newValue) ->
                        sheetComponentController.updateCellContent(selectCellForEditCombobox.getSelectionModel().getSelectedItem(), newValue));

        // alignment box
        ObservableList<String> options =
                FXCollections.observableArrayList( "Left", "Center", "Right" );
        alignmentBox.setItems(options);

        // mark checkbox listener
        markCheckbox.selectedProperty().addListener((observableValue, old, newValue) -> {
            sheetComponentController.markCellsButtonActionListener(newValue);
        });

        // toggle color checkbox listener
        toggleColorCheckbox.selectedProperty().addListener((observableValue, old, newValue) -> {
            sheetComponentController.toggleCellColor(newValue);
        });

        // column width picker
        widthPicker
                .valueProperty()
                .addListener((observable, oldValue, newValue) -> sheetComponentController.changeSecondColumnWidth(newValue));

        SpinnerValueFactory<Integer> widthValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 200, 100, 1);
        widthPicker.setValueFactory(widthValueFactory);

        // row height picker
        heightPicker
                .valueProperty()
                .addListener((observable, oldValue, newValue) -> sheetComponentController.changeSecondRowWidth(newValue));

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
                sheetComponentController.alignCells(javafx.geometry.Pos.CENTER_LEFT);
                break;
            case 1:
                sheetComponentController.alignCells(Pos.CENTER);
                break;
            case 2:
                sheetComponentController.alignCells(Pos.CENTER_RIGHT);
                break;
        }
    }

}
