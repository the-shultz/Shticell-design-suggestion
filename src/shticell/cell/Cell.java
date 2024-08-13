package shticell.cell;

import java.util.List;

public interface Cell {
    Coordinate getCoordinate();
    String originalValue();
    Object getEffectiveValue();
    int version();
    List<Cell> getDependsOn();
    List<Cell> getInfluencingOn();
}