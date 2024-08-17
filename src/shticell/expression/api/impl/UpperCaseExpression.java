package shticell.expression.api.impl;

import shticell.expression.api.Expression;
import shticell.sheet.api.CellType;
import shticell.sheet.api.EffectiveValue;
import shticell.sheet.impl.EffectiveValueImpl;

public class UpperCaseExpression implements Expression {

    private final Expression e;

    public UpperCaseExpression(Expression value) {
        this.e = value;
    }

    @Override
    public EffectiveValue eval() {
        EffectiveValue eval = e.eval();
        String upperCaseResult = eval.extractValueWithExpectation(String.class).toUpperCase();
        return new EffectiveValueImpl(CellType.STRING, upperCaseResult);
    }

    @Override
    public CellType getFunctionResultType() {
        return CellType.STRING;
    }
}
