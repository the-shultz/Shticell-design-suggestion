package shticell;

import shticell.sheet.api.Sheet;
import shticell.cell.api.Cell;
import shticell.sheet.impl.SheetImpl;

public class Main {
    public static void main(String[] args) {
        Sheet sheet = new SheetImpl();
        sheet.updateCellValueAndCalculate(0, 0, "Hello, World!");

        Cell cell = sheet.getCell(0, 0);
        cell.calculateEffectiveValue();
        Object value = cell.getEffectiveValue().getValue();
        System.out.println(value);

        sheet.updateCellValueAndCalculate(1,1, "{plus, 1, 2}");

        cell = sheet.getCell(1, 1);

        cell.calculateEffectiveValue();
        Double result = cell.getEffectiveValue().extractValueWithExpectation(Double.class);
        System.out.println("result: " + result);
    }
}
