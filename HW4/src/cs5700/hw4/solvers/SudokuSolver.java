package cs5700.hw4.solvers;

import cs5700.hw4.sudoku.Board;
import cs5700.hw4.sudoku.Group;

import java.util.ArrayList;

public abstract class SudokuSolver {

    int size;

    int sqrtSize;

    Board board;

    ArrayList<CellIndex> cases;

    private boolean successful;

    boolean isGuess;

    private ArrayList<String> possibilities;

    public SudokuSolver() {
        this.board = Board.getInstance();
        this.size = board.getSize();
        this.sqrtSize = (int) Math.sqrt(size);
        this.possibilities = new ArrayList<>();
        this.cases = new ArrayList<>();
        this.successful = true;
        this.isGuess = false;
    }

    public boolean solve() {
        cases.clear();
        successful = false;
        updateBoard();
        if (findCase()) {
            successful = solveCase();
        }
        return successful;
    }

    public void updateBoard() {
        for (int i = 0; i < size; i++) {
            board.getRow(i).updatePossibilities();
            board.getColumn(i).updatePossibilities();
            board.getBlock(i).updatePossibilities();
        }

        board.setSolved(true);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getRow(i).getCell(j).isEmpty()) {
                    board.setSolved(false);
                    possibilities = Board.getInstance().getSymbols();
                    possibilities.removeAll(board.getRow(i).getCurrentSymbols());
                    possibilities.removeAll(board.getColumn(j).getCurrentSymbols());
                    int blockIndex = board.getBlockIndex(j, i);
                    possibilities.removeAll(board.getBlock(blockIndex).getCurrentSymbols());
                    board.getRow(i).getCell(j).addAllPossibleSymbols(possibilities);
                }
            }
        }

        updateRowInteraction();
        updateNakedSubsets();
    }

    private void updateRowInteraction() {
        for (int i = 0; i < size; i++) {
            ArrayList<String> possibilities = board.getBlock(i).getPossibleSymbols();
            for (String s : possibilities) {
                ArrayList<Integer> locations = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    if (board.getBlock(i).getCell(j).isPossible(s)) {
                        locations.add(j);
                    }
                }
                isolateRowCol(locations, s, i);
            }
        }
    }

    private void isolateRowCol(ArrayList<Integer> locations, String s, int blockID) {
        if (locations.size() <= sqrtSize && locations.size() != 0) {
            boolean singleRow = true;
            boolean singleCol = true;
            int startRow = locations.get(0) / sqrtSize;
            int startCol = locations.get(0);
            for (Integer k : locations) {
                if (k / sqrtSize != startRow) {
                    singleRow = false;
                }
                if ((k - startCol) % sqrtSize != 0) {
                    singleCol = false;
                }
            }
            if (singleRow) {
                removeRowPossibilities(startRow, s, blockID);
            }
            if (singleCol) {
                removeColPossibilities(startCol, s, blockID);
            }
        }
    }

    private void removeRowPossibilities(int startRow, String s, int blockID) {
        int boardRow = (blockID / sqrtSize) * sqrtSize + startRow;
        for (int k = 0; k < size; k++) {
            if (board.getRow(boardRow).getCell(k).isEmpty()) {
                if (k < (blockID % sqrtSize) * sqrtSize || k > ((blockID % sqrtSize) * sqrtSize) + (sqrtSize - 1)) {
                    board.getRow(boardRow).getCell(k).removePossibility(s);
                }
            }
        }
    }

    private void removeColPossibilities(int startCol, String s, int blockID) {
        int boardCol = (blockID % sqrtSize) * sqrtSize + (startCol % sqrtSize);
        for (int k = 0; k < size; k++) {
            if (board.getColumn(boardCol).getCell(k).isEmpty()) {
                if (k < (blockID / sqrtSize) * sqrtSize || k > ((blockID / sqrtSize) * sqrtSize) + (sqrtSize - 1)) {
                    board.getColumn(boardCol).getCell(k).removePossibility(s);
                }
            }
        }
    }

    private void updateNakedSubsets() {
        for (int i = 0; i < size; i++) {
            removeNakedSubsets(board.getRow(i));
            removeNakedSubsets(board.getColumn(i));
            removeNakedSubsets(board.getBlock(i));
        }
    }

    private void removeNakedSubsets(Group group) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ArrayList<String> one = group.getCell(j).getPossibleSymbols();
                if (one.size() == 2) {
                    for (int k = j+1; k < size; k++) {
                        if (one.equals(group.getCell(k).getPossibleSymbols())) {
                            for (int m = 0; m < size; m++) {
                                if (m != k && m != j && group.getCell(m).isEmpty()) {
                                    group.getCell(m).removeAllPossibilities(one);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    abstract boolean findCase();

    abstract boolean solveCase();

    public boolean isSuccessful() {
        return successful;
    }

    class CellIndex {
        int row;
        int col;
        int block;
        int cell;
        String symbol;
        CellIndex(int row, int col) {
            this.row = row;
            this.col = col;
        }
        CellIndex(int block, int cell, String symbol) {
            this.block = block;
            this.cell = cell;
            this.symbol = symbol;
        }
    }
}
