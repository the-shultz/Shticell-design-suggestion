package shticell.expression.api;

import shticell.cell.api.CellType;
import shticell.cell.api.EffectiveValue;

public interface Expression {
    EffectiveValue eval();
    CellType getFunctionResultType();
}
