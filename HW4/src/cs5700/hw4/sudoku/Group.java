package cs5700.hw4.sudoku;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public abstract class Group {

    private int size = 0;

    private ArrayList<Cell> cellGroup;

    private ArrayList<String> possibleSymbols;

    private boolean onlyOnePossible = false;

    public Group(LinkedHashSet group) {
        this.size = group.size();
        this.cellGroup = new ArrayList<>();
        this.cellGroup.addAll(group);
        this.possibleSymbols = Board.getInstance().getSymbols();
        updatePossibilities();
    }

    public int getSize() {
        return size;
    }

    public boolean contains(String symbol) {
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
        possibleSymbols = Board.getInstance().getSymbols();
        for (Cell c : cellGroup) {
            if (!c.isEmpty()) {
                possibleSymbols.remove(c.getSymbol());
            }
        }

        if (possibleSymbols.size() == 1) {
            onlyOnePossible = true;
        }
    }

    public boolean isOnlyOnePossible() {
        return onlyOnePossible;
    }

    public String getPossibleSymbol() {
        if (possibleSymbols.isEmpty()) {
            return null;
        } else {
            return possibleSymbols.remove(0);
        }
    }

    public ArrayList<String> getPossibleSymbols() { return possibleSymbols; }

    public ArrayList<String> getCurrentSymbols() {
        ArrayList<String> currentSymbols = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (!cellGroup.get(i).isEmpty()) {
                currentSymbols.add(cellGroup.get(i).getSymbol());
            }
        }
        return currentSymbols;
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

    abstract String groupFormat(int i);
}
