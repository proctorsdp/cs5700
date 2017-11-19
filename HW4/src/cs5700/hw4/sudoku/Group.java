package cs5700.hw4.sudoku;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Group {

    private int size = 0;

    private ArrayList<Cell> cellGroup;

    private ArrayList<String> possibleSymbols;

    private boolean onlyOnePossible = false;

    public Group(ArrayList<Cell> group) {
        this.size = group.size();
        this.cellGroup = group;
        this.possibleSymbols = (ArrayList<String>) Board.getInstance().getSymbols().clone();
        updatePossibilities();
    }

    abstract String groupFormat(int i);

    public int numEmptyCells() {
        int emptyCells = 0;
        for (Cell c: cellGroup) {
            if (c.isEmpty()) {
                emptyCells++;
            }
        }
        return emptyCells;
    }

    public int getSize() {
        return size;
    }

    public Iterator getIterator() { return cellGroup.iterator(); }

    public <T> boolean contains(T symbol) {
        for (int i = 0; i < size; i++) {
            if (cellGroup.get(i).getSymbol().equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    public Cell getCell(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return cellGroup.get(index);
        }
    }

    public void updatePossibilities() {
        for (Cell c : cellGroup) {
            if (!c.isEmpty()) {
                possibleSymbols.remove(c.getSymbol());
            }
        }

        if (possibleSymbols.size() == 1) { onlyOnePossible = true; }

        for (Cell c : cellGroup) {
            if (c.isEmpty()) {
                c.addPossibleSymbol(possibleSymbols);
            }
        }
    }

    public boolean isOnlyOnePossible() {
        return onlyOnePossible;
    }

    @Override
    public String toString() {
        StringBuilder group = new StringBuilder();
        for (int i = 0; i < size; i++) {
            group.append(cellGroup.get(i).toString());
            group.append(groupFormat(i));
        }
        return group.toString();
    }
}
