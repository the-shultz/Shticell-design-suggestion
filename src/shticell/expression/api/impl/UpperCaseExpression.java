package shticell.expression.api.impl;

import shticell.expression.api.Expression;

public class UpperCaseExpression implements Expression<String> {

    private final String value;

    public UpperCaseExpression(String value) {
        this.value = value;
    }

    @Override
    public String eval() {
        return value.toUpperCase();
    }
}
