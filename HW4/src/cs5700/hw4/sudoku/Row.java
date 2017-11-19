package cs5700.hw4.sudoku;

import java.util.ArrayList;

public class Row extends Group {

    private int rowIndex;

    public Row(ArrayList<Cell> row, int rowIndex) {
        super(row);
        this.rowIndex = rowIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    String groupFormat(int i) {
        if (++i == getSize()) {
            return " \n";
        } else {
            return " ";
        }
    }
}
