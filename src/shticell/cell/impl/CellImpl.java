package shticell.cell.impl;

import shticell.cell.api.EffectiveValue;
import shticell.cell.api.Cell;
import shticell.coordinate.Coordinate;
import shticell.coordinate.CoordinateImpl;
import shticell.expression.api.Expression;
import shticell.expression.parser.FunctionParser;
import shticell.sheet.api.Sheet;
import shticell.sheet.api.SheetReadActions;

import java.util.ArrayList;
import java.util.List;

public class CellImpl implements Cell {

    private final Coordinate coordinate;
    private String originalValue;
    private EffectiveValue effectiveValue;
    private int version;
    private final List<Cell> dependsOn;
    private final List<Cell> influencingOn;
    private final SheetReadActions sheet;

    public CellImpl(int row, int column, String originalValue, int version, SheetReadActions sheet)  {
        this.sheet = sheet;
        this.coordinate = new CoordinateImpl(row, column);
        this.originalValue = originalValue;
        this.version = version;
        this.dependsOn = new ArrayList<>();
        this.influencingOn = new ArrayList<>();
    }
    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String getOriginalValue() {
        return originalValue;
    }

    @Override
    public void setCellOriginalValue(String value) {
        this.originalValue = value;
    }

    @Override
    public EffectiveValue getEffectiveValue() {
        return effectiveValue;
    }

    @Override
    public void calculateEffectiveValue() {
        // build the expression object out of the original value...
        // it can be {PLUS, 4, 5} OR {CONCAT, {ref, A4}, world}
        Expression expression = FunctionParser.parseExpression(originalValue);

        effectiveValue = expression.eval(sheet);
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public List<Cell> getDependsOn() {
        return dependsOn;
    }

    @Override
    public List<Cell> getInfluencingOn() {
        return influencingOn;
    }
}
