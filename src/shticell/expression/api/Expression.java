package shticell.expression.api;

import shticell.sheet.api.CellType;
import shticell.sheet.api.EffectiveValue;

public interface Expression {
    EffectiveValue eval();
    CellType getFunctionResultType();
}
