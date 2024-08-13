package shticell.sheet.api;

import shticell.sheet.cell.api.Cell;

public interface Sheet {
    int getVersion();
    Cell getCell(int row, int column);
    void setCell(int row, int column, String value);

}
