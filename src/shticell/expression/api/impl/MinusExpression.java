package shticell.expression.api.impl;

import shticell.expression.api.Expression;
import shticell.sheet.api.CellType;
import shticell.sheet.api.EffectiveValue;
import shticell.sheet.impl.EffectiveValueImpl;

public class MinusExpression implements Expression {

    private Expression left;
    private Expression right;

    public MinusExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public EffectiveValue eval() {
        EffectiveValue leftValue = left.eval();
        EffectiveValue rightValue = right.eval();
        // do some checking... error handling...
        //double result = (Double) leftValue.getValue() + (Double) rightValue.getValue();
        double result = leftValue.extractValueWithExpectation(Double.class) - rightValue.extractValueWithExpectation(Double.class);

        return new EffectiveValueImpl(CellType.NUMERIC, result);

    }

    @Override
    public CellType getFunctionResultType() {
        return CellType.NUMERIC;
    }
}
