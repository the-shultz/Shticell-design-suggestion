package shticell.expression.api.impl;

import shticell.expression.api.Expression;

public class PlusExpression implements Expression<Double> {

    private Expression<Double> left;
    private Expression<Double> right;

    public PlusExpression(Expression<Double> left, Expression<Double> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Double eval() {
        return left.eval() + right.eval();
    }
}
