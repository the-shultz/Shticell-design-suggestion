package shticell.cell.impl;

import shticell.cell.api.Cell;
import shticell.cell.api.Coordinate;

import java.util.List;

public class CellImpl implements Cell {

    private final Coordinate coordinate;
    private String originalValue;
    private Object effectiveValue;
    private int version;
    private final List<Cell> dependsOn;
    private final List<Cell> influencingOn;

    public CellImpl(int row, int column, String originalValue, Object effectiveValue, int version, List<Cell> dependsOn, List<Cell> influencingOn) {
        this.coordinate = new CoordinateImpl(row, column);
        this.originalValue = originalValue;
        this.effectiveValue = effectiveValue;
        this.version = version;
        this.dependsOn = dependsOn;
        this.influencingOn = influencingOn;
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
    public Object getEffectiveValue() {
        return effectiveValue;
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
