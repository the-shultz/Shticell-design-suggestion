package shticell.sheet.impl;

import shticell.cell.api.Cell;
import shticell.cell.api.Coordinate;
import shticell.cell.impl.CoordinateImpl;
import shticell.sheet.api.Sheet;

import java.util.HashMap;
import java.util.Map;

public class SheetImpl implements Sheet {

    private Map<Coordinate, Cell> activeCells;

    public SheetImpl() {
        this.activeCells = new HashMap<>();
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Cell getCell(int row, int column) {
        return activeCells.get(new CoordinateImpl(row, column));
    }

    @Override
    public void setCell(int row, int column, String value) {
        Coordinate coordinate = new CoordinateImpl(row, column);
        Cell cell = activeCells.get(coordinate);
        cell.setCellOriginalValue(value);
    }
}
