package shticell.expression.api.impl;

import shticell.expression.api.Expression;
import shticell.sheet.api.CellType;
import shticell.sheet.api.EffectiveValue;
import shticell.sheet.impl.EffectiveValueImpl;

public class IdentityExpression implements Expression {

    private final Object value;
    private final CellType type;

    public IdentityExpression(Object value, CellType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public EffectiveValue eval() {
        return new EffectiveValueImpl(type, value);
    }

    @Override
    public CellType getFunctionResultType() {
        return type;
    }
}
