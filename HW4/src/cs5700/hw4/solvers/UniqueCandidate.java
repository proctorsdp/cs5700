package cs5700.hw4.solvers;


import cs5700.hw4.sudoku.Cell;

import java.util.ArrayList;

public class UniqueCandidate extends SudokuSolver {

    private static UniqueCandidate instance;

    private UniqueCandidate() {}

    public static UniqueCandidate getInstance() {
        if (instance == null) {
            instance = new UniqueCandidate();
        }
        return instance;
    }

    @Override
    public boolean findCase() {
        boolean foundCase = false;
        for (int i = 0; i < size; i++) {
            ArrayList<String> possibilities = board.getBlock(i).getPossibleSymbols();
            for (String possibility : possibilities) {
                ArrayList<CellIndex> potentialCases = new ArrayList<>();
                for (int k = 0; k < size; k++) {
                    if (board.getBlock(i).getCell(k).isPossible(possibility)) {
                        potentialCases.add(new CellIndex(i, k, possibility));
                    }
                }
                if (potentialCases.size() == 1) {
                    cases.addAll(potentialCases);
                    foundCase = true;
                }
            }
        }
        return foundCase;
    }

    @Override
    public boolean solveCase() {
        boolean solved = false;
        for (CellIndex index : cases) {
            Cell cell = board.getBlock(index.block).getCell(index.cell);
            String symbol = cell.getPossibleSymbol();
            if (!board.getRow(index.row).contains(symbol) &&
                    !board.getColumn(index.col).contains(symbol) &&
                    !board.getBlock(board.getBlockIndex(index.col, index.row)).contains(symbol)) {
                cell.setSymbol(index.symbol, isGuess);
                solved = true;
            }
        }
        return solved;
    }
}
