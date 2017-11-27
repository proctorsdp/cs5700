package cs5700.hw4.solvers;

import cs5700.hw4.sudoku.Cell;

public class SoleCandidate extends SudokuSolver {

    private static SoleCandidate instance;

    private SoleCandidate() {}

    public static SoleCandidate getInstance() {
        if (instance == null) {
            instance = new SoleCandidate();
        }
        return instance;
    }

    @Override
    public boolean findCase() {
        boolean foundCase = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell c = board.getRow(i).getCell(j);
                if (c.isEmpty() && c.numPossibilities() == 1) {
                    cases.add(new CellIndex(i, j));
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
            Cell cell = board.getRow(index.row).getCell(index.col);
            String symbol = cell.getPossibleSymbol();
            if (!board.getRow(index.row).contains(symbol) &&
                    !board.getColumn(index.col).contains(symbol) &&
                    !board.getBlock(board.getBlockIndex(index.col, index.row)).contains(symbol)) {
                cell.setSymbol(symbol, isGuess);
                solved = true;
            }
        }
        return solved;
    }
}
