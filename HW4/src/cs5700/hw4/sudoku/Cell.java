package cs5700.hw4.sudoku;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cell {

    private LinkedList<String> possibleSymbols = new LinkedList<>();

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
        if (isGuess) {
            possibleSymbols.add(symbol);
        }
        this.symbol = symbol;
        this.isGuess = isGuess;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPossibleSymbol() {
        if (possibleSymbols.isEmpty()) {
            return null;
        } else {
            return possibleSymbols.remove();
        }
    }

    public void addPossibleSymbol(String possibleSymbol) {
        if (!possibleSymbols.contains(possibleSymbol)) {
            possibleSymbols.add(possibleSymbol);
        }
    }

    public void addPossibleSymbol(ArrayList<String> symbols) {
        for (String s : symbols) {
            if (!possibleSymbols.contains(s)) {
                possibleSymbols.add(s);
            }
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isGuess() { return isGuess; }


    @Override
    public String toString() {
        return symbol;
    }
}
