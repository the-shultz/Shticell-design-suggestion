package shticell.sheet.api;

import shticell.cell.api.Cell;

public interface SheetReadActions {
    int getVersion();
    Cell getCell(int row, int column);

}
