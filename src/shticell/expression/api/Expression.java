package shticell.expression.api;

import shticell.cell.api.CellType;
import shticell.cell.api.EffectiveValue;
import shticell.sheet.api.Sheet;

public interface Expression {
    EffectiveValue eval(Sheet sheet);
    CellType getFunctionResultType();
}
