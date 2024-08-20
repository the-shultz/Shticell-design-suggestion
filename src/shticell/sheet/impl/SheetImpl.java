package shticell.sheet.impl;

import shticell.cell.impl.CellImpl;
import shticell.sheet.api.Sheet;
import shticell.cell.api.Cell;
import shticell.coordinate.Coordinate;
import shticell.coordinate.CoordinateFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        return activeCells.get(CoordinateFactory.createCoordinate(row, column));
    }

    @Override
    public void setCell(int row, int column, String value) {
        Coordinate coordinate = CoordinateFactory.createCoordinate(row, column);
        Optional
            .ofNullable(activeCells.get(coordinate))
            .or(() -> {
                    Cell newCell = new CellImpl(row, column, value, 1, this);
                    activeCells.put(coordinate, newCell);
                    return Optional.of(newCell);
                })
            .ifPresent(cell -> cell.setCellOriginalValue(value));
    }
}
