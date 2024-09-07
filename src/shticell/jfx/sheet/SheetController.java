package shticell.jfx.sheet;

public interface SheetController {
    void alignCells(javafx.geometry.Pos alignment);

    void markCellsButtonActionListener(boolean isMarked);

    void toggleCellColor(boolean isSelected);

    void changeSecondColumnWidth(double width);

    void changeSecondRowWidth(double width);

    void updateCellContent(String cellId, String content);
}
