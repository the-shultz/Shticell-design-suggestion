package shticell.expression.api;

import shticell.cell.api.CellType;
import shticell.cell.api.EffectiveValue;
import shticell.sheet.api.SheetReadActions;

public interface Expression {
    EffectiveValue eval(SheetReadActions sheet);
    CellType getFunctionResultType();
}
