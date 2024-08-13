package shticell.sheet.coordinate;

public class CoordinateFactory {

    public static Coordinate createCoordinate(int row, int column) {
        return new CoordinateImpl(row, column);
    }
}
