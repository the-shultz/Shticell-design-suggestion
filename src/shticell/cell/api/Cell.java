package shticell.cell.api;

import shticell.coordinate.Coordinate;

import java.util.List;

public interface Cell {
    Coordinate getCoordinate();
    String getOriginalValue();
    void setCellOriginalValue(String value);
    EffectiveValue getEffectiveValue();
    void calculateEffectiveValue();
    int getVersion();
    List<Cell> getDependsOn();
    List<Cell> getInfluencingOn();
}