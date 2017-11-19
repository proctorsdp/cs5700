package cs5700.hw4.sudoku;

import java.util.ArrayList;

public class Column extends Group {

    private int columnIndex;

    public Column(ArrayList<Cell> column, int columnIndex) {
        super(column);
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    String groupFormat(int i) {
        return " \n";
    }
}
