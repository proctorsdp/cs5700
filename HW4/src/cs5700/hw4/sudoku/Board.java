package cs5700.hw4.sudoku;


import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Board {

    private static Board instance;

    private ArrayList<Block> blocks = new ArrayList<>();

    private ArrayList<Row> rows = new ArrayList<>();

    private ArrayList<Column> columns = new ArrayList<>();

    private ArrayList<String> symbols = new ArrayList<>();

    private int size;

    private int sqrtSize;

    private boolean isSolved;

    private Board() {}

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void initBoard(
            int size, LinkedHashSet symbols, LinkedHashSet[] rows, LinkedHashSet[] cols, LinkedHashSet[][] blocks) {
        this.size = size;
        this.sqrtSize = (int) Math.sqrt(size);
        this.symbols.addAll(symbols);
        this.isSolved = false;

        for (int i = 0; i < size; i++) {
            this.rows.add(new Row(rows[i], i));
            this.columns.add(new Column(cols[i], i));
        }

        for (int i = 0; i < sqrtSize; i++) {
            LinkedHashSet b = null;
            for (int j = 0; j < sqrtSize; j++) {
                b = blocks[j][i];
                this.blocks.add(new Block(b, i));
            }
        }
    }

    public ArrayList<String> getSymbols() {
        return (ArrayList<String>) symbols.clone();
    }

    private boolean outOfRange(int i) {
        return i < 0 || i >= size;
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

    public int getBlockIndex(int col, int row) {
        return (row / sqrtSize) * sqrtSize + col / sqrtSize;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (Row r : rows) {
            board.append(r.toString());
        }
        return board.toString();
    }
}
