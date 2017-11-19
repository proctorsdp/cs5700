package cs5700.hw4.sudoku;

import java.util.ArrayList;

public class Board {

    private static Board instance;

    private ArrayList<Block> blocks = new ArrayList<>();

    private ArrayList<Row> rows = new ArrayList<>();

    private ArrayList<Column> columns = new ArrayList<>();

    private ArrayList<String> symbols = new ArrayList<>();

    private int size;

    private Board() {}

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void initBoard(ArrayList<ArrayList<Cell>> puzzle, ArrayList<String> symbols) {
        this.size = puzzle.size();
        this.symbols = symbols;

        initRowsAndCols(puzzle);
        initBlocks(puzzle);
    }

    private void initRowsAndCols(ArrayList<ArrayList<Cell>> puzzle) {
        for (int i = 0; i < size; i++) {
            columns.add(new Column(puzzle.get(i), i));

            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(puzzle.get(j).get(i));
            }
            rows.add(new Row(row, i));
        }
    }

    private void initBlocks(ArrayList<ArrayList<Cell>> puzzle) {
        int colCount = 0;
        int rowCount = 0;

        for (int k = 1; k <= size; k ++) {
            ArrayList<Cell> block = new ArrayList<>();

            for (int i = (int) (Math.sqrt(size) * rowCount); i < Math.sqrt(size) * (rowCount + 1); i++) {
                for (int j = (int) (Math.sqrt(size) * colCount); j < Math.sqrt(size) * (colCount + 1); j++) {
                    block.add(puzzle.get(j).get(i));
                }
            }

            blocks.add(new Block(block, blocks.size() + 1));
            colCount++;

            if (k % Math.sqrt(size) == 0) {
                rowCount++;
                colCount = 0;
            }
        }
    }

    public ArrayList<String> getSymbols() {
        return symbols;
    }

    private boolean outOfRange(int i) {
        return i < 0 || i >= size;
    }

    public boolean isValidSymbol(String symbol) {
        for (int i = 0; i < size; i++) {
            if (symbols.get(i).toString().equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public Block getBlock(int index) {
        return outOfRange(index) ? null : blocks.get(index);
    }

    public Row getRow(int index) {
        return outOfRange(index) ? null : rows.get(index);
    }

    public Column getColumn(int index) {
        return outOfRange(index) ? null : columns.get(index);
    }

    public String toString() {
        StringBuilder board = new StringBuilder();
        for (Row r : rows) {
            board.append(r.toString());
        }
        return board.toString();
    }
}
