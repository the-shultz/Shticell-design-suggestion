package shticell;

import shticell.sheet.api.Sheet;
import shticell.sheet.cell.api.Cell;
import shticell.sheet.impl.SheetImpl;

public class Main {
    public static void main(String[] args) {
        Sheet sheet = new SheetImpl();
        sheet.setCell(0, 0, "Hello, World!");

        Cell cell = sheet.getCell(0, 0);
        Object value = cell.getEffectiveValue().getValue();
        System.out.println(value);
    }
}
