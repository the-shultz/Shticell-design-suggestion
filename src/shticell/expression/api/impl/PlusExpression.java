package shticell.expression.api.impl;

import shticell.expression.api.Expression;
import shticell.sheet.api.CellType;
import shticell.sheet.api.EffectiveValue;
import shticell.sheet.impl.EffectiveValueImpl;

public class PlusExpression implements Expression {

    private Expression left;
    private Expression right;

    public PlusExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public EffectiveValue eval() {
        EffectiveValue leftValue = left.eval();
        EffectiveValue rightValue = right.eval();
        // do some checking... error handling...
        double result = (Double) leftValue.getValue() + (Double) rightValue.getValue();

        return new EffectiveValueImpl(CellType.NUMERIC, result);
    }
}
