package shticell.sheet.api;

public interface SheetUpdateActions {
    Sheet updateCellValueAndCalculate(int row, int column, String value);
}
