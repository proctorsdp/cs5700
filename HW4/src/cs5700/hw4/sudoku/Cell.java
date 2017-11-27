package cs5700.hw4.sudoku;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Cell {

    private ArrayList<String> possibleSymbols = new ArrayList<>();

    private boolean isEmpty = true;

    private boolean isGuess = false;

    private String symbol;

    public Cell(String symbol) {
        if (!symbol.equals("-")) {
            isEmpty = false;
        }
        this.symbol = symbol;
    }

    public void setSymbol(String symbol, boolean isGuess) {
        possibleSymbols.clear();
        this.symbol = symbol;
        this.isGuess = isGuess;
        this.isEmpty = false;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPossibleSymbol() {
        if (possibleSymbols.isEmpty()) {
            return null;
        } else {
            return possibleSymbols.remove(0);
        }
    }

    public void addPossibleSymbol(String possibleSymbol) {
        if (!possibleSymbols.contains(possibleSymbol)) {
            possibleSymbols.add(possibleSymbol);
        }
    }

    public void addAllPossibleSymbols(ArrayList<String> symbols) {
        possibleSymbols.clear();
        for (String s : symbols) {
            if (!possibleSymbols.contains(s)) {
                possibleSymbols.add(s);
            }
        }
    }

    public void removePossibility(String symbol) {
        possibleSymbols.remove(symbol);
    }

    public void removeAllPossibilities(ArrayList<String> symbols) {
        possibleSymbols.removeAll(symbols);
    }

    public void emptyCell() {
        symbol = "-";
        isEmpty = true;
    }

    public ArrayList<String> getPossibleSymbols() { return possibleSymbols; }

    public boolean isPossible(String symbol) { return possibleSymbols.contains(symbol); }

    public int numPossibilities() { return possibleSymbols.size(); }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isGuess() { return isGuess; }


    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Cell && !isEmpty && Objects.equals(((Cell) object).symbol, symbol);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }
}
