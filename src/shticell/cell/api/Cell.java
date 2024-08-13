package shticell.cell.api;

import java.util.List;

public interface Cell {
    Coordinate getCoordinate();
    String getOriginalValue();
    void setCellOriginalValue(String value);
    Object getEffectiveValue();
    int getVersion();
    List<Cell> getDependsOn();
    List<Cell> getInfluencingOn();
}