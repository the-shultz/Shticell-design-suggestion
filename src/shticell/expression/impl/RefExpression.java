package shticell.expression.impl;

import shticell.cell.api.CellType;
import shticell.cell.api.EffectiveValue;
import shticell.coordinate.Coordinate;
import shticell.expression.api.Expression;
import shticell.sheet.api.Sheet;
import shticell.sheet.api.SheetReadActions;

public class RefExpression implements Expression {

    private final Coordinate coordinate;

    public RefExpression(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public EffectiveValue eval(SheetReadActions sheet) {
        // error handling if the cell is empty or not found
        return sheet.getCell(coordinate.getRow(), coordinate.getColumn()).getEffectiveValue();
    }

    @Override
    public CellType getFunctionResultType() {
        return CellType.UNKNOWN;
    }
}
