package shticell;

import shticell.sheet.api.Sheet;
import shticell.sheet.impl.SheetImpl;

public class Main {
    public static void main(String[] args) {
        Sheet sheet = new SheetImpl();
        sheet.setCell(0, 0, "Hello, World!");
    }
}
