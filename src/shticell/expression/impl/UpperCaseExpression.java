package shticell.expression.impl;

import shticell.expression.api.Expression;
import shticell.cell.api.CellType;
import shticell.cell.api.EffectiveValue;
import shticell.cell.impl.EffectiveValueImpl;
import shticell.sheet.api.Sheet;
import shticell.sheet.api.SheetReadActions;

public class UpperCaseExpression implements Expression {

    private final Expression e;

    public UpperCaseExpression(Expression value) {
        this.e = value;
    }

    @Override
    public EffectiveValue eval(SheetReadActions sheet) {
        EffectiveValue eval = e.eval(sheet);
        String upperCaseResult = eval.extractValueWithExpectation(String.class).toUpperCase();
        return new EffectiveValueImpl(CellType.STRING, upperCaseResult);
    }

    @Override
    public CellType getFunctionResultType() {
        return CellType.STRING;
    }
}
