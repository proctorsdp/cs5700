package cs5700.hw4.solvers;

import cs5700.hw4.sudoku.Cell;

import java.util.ArrayList;

public class GuessSymbol extends SudokuSolver {

    private static GuessSymbol instance;

    private GuessSymbol() {}

    public static GuessSymbol getInstance() {
        if (instance == null) {
            instance = new GuessSymbol();
        }
        return instance;
    }

    private ArrayList<Guess> guessCases = new ArrayList<>();

    private Guess swapCase;

    private Guess guessCase;

    @Override
    public boolean findCase() {
        swapCase = null;
        guessCase = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell c = board.getRow(i).getCell(j);
                if (c.getPossibleSymbols().size() == 2 && !guessCases.contains(new Guess(c))) {
                    Guess newGuess = new Guess(c);
                    guessCases.add(newGuess);
                    guessCase = newGuess;
                    return true;
                }
            }
        }
        if (!guessCases.isEmpty()) {
            removeGuesses();
            System.out.println("Removed Deductions based on Guesses:");
            System.out.println(board);
            for (int i = guessCases.size()-1; i >= 0; i--) {
                if (!guessCases.get(i).hasBeenSwapped()) {
                    swapCase = guessCases.get(i);
                    return true;
                } else {
                    guessCases.get(i).empty();
                    guessCases.remove(i);
                }
            }
        }
        return false;
    }

    @Override
    public boolean solveCase() {
        boolean solved = false;
        if (swapCase != null || guessCase != null) {
            isGuess = true;
            solved = true;
            SoleCandidate.getInstance().isGuess = true;
            UniqueCandidate.getInstance().isGuess = true;
            if (swapCase != null) {
                swapCase.swapGuess();
            } else if (guessCase != null) {
                guessCase.guess();
            }
        }

        return solved;
    }

    private void removeGuesses() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell c = board.getRow(i).getCell(j);
                if (c.isGuess() & !guessCases.contains(new Guess(c))) {
                    c.emptyCell();
                }
            }
        }
    }

    private class Guess {
        Cell cell;
        String symbol;
        String otherSymbol;
        boolean beenSwapped;

        Guess(Cell cell) {
            this.cell = cell;
            if (cell.getPossibleSymbols().size() == 2) {
                this.symbol = cell.getPossibleSymbols().get(0);
                this.otherSymbol = cell.getPossibleSymbols().get(1);
            }
            this.beenSwapped = false;
        }

        void swapGuess() {
            if (!beenSwapped) {
                cell.setSymbol(otherSymbol, isGuess);
            }
            beenSwapped = true;
        }

        void guess() {
            if (!beenSwapped) {
                cell.setSymbol(symbol, isGuess);
            }
        }

        void empty() {
            cell.emptyCell();
        }

        boolean hasBeenSwapped() {
            return beenSwapped;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Guess) {
                Guess guess = (Guess) obj;
                return (guess.cell).equals(this.cell);
            }
            return false;
        }
    }
}
