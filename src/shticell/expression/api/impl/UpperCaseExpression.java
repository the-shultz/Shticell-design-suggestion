package shticell.expression.api.impl;

import shticell.expression.api.Expression;
import shticell.sheet.api.CellType;
import shticell.sheet.api.EffectiveValue;
import shticell.sheet.impl.EffectiveValueImpl;

public class UpperCaseExpression implements Expression {

    private final String value;

    public UpperCaseExpression(String value) {
        this.value = value;
    }

    @Override
    public EffectiveValue eval() {
        return new EffectiveValueImpl(CellType.STRING, value.toUpperCase());
    }
}
