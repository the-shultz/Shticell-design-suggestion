package shticell.sheet.impl;

import shticell.cell.impl.CellImpl;
import shticell.sheet.api.Sheet;
import shticell.cell.api.Cell;
import shticell.coordinate.Coordinate;
import shticell.coordinate.CoordinateFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
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
    public Sheet updateCellValueAndCalculate(int row, int column, String value) {

        Coordinate coordinate = CoordinateFactory.createCoordinate(row, column);

        SheetImpl newSheetVersion = copySheet();
        Cell newCell = new CellImpl(row, column, value, newSheetVersion.getVersion(), newSheetVersion);
        newSheetVersion.activeCells.put(coordinate, newCell);

        try {
            List<Cell> cellsCalculationOrder = newSheetVersion.orderCellsForCalculation();
            cellsCalculationOrder.forEach(Cell::calculateEffectiveValue);
            // successful calculation. update sheet version
            // newSheetVersion.increaseVersion();
            return newSheetVersion;
        } catch (Exception e) {
            // deal with the runtime error that was discovered as part of invocation
            return this;
        }
    }

    private List<Cell> orderCellsForCalculation() {
        // data structure 1 0 1: Topological sort...
        // build graph from the cells. each cell is a node. each cell that has ref(s) constitutes an edge
        // handle case of circular dependencies -> should fail
        return null;
    }

    private SheetImpl copySheet() {
        // lots of options here:
        // 1. implement clone all the way (yac... !)
        // 2. implement copy constructor for CellImpl and SheetImpl

        // 3. how about serialization ?
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            return (SheetImpl) ois.readObject();
        } catch (Exception e) {
            // deal with the runtime error that was discovered as part of invocation
            return this;
        }
    }
}