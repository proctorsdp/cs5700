package cs5700.hw4.unitTests;

import cs5700.hw4.solvers.UniqueCandidate;
import cs5700.hw4.sudoku.Board;
import cs5700.hw4.sudoku.Cell;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashSet;

class UniqueCandidateTest {

    private Board board;

    private UniqueCandidate uniqueCandidate;

    @BeforeEach
    void setUp() {
        board = Board.getInstance();
        int size = 4;
        int sqrt = (int) Math.sqrt(size);

        LinkedHashSet<Cell> empty = new LinkedHashSet<>();
        LinkedHashSet<String> symbols = new LinkedHashSet<>();
        LinkedHashSet[] emptyGroups = new LinkedHashSet[size];

        for (int i = 1; i < size+1; i++) {
            symbols.add("" + i);
            empty.add(new Cell("-"));
        }

        for (int j = 0; j < size; j++) {
            emptyGroups[j] = empty;
        }

        LinkedHashSet[][] blockGroup = new LinkedHashSet[sqrt][sqrt];
        for (int i = 0 ; i < sqrt; i++) {
            for (int j = 0; j < sqrt; j++) {
                blockGroup[i][j] = empty;
            }
        }

        board.initBoard(size, symbols, emptyGroups, emptyGroups, blockGroup);
        uniqueCandidate = UniqueCandidate.getInstance();

        board.getRow(0).getCell(0).setSymbol("4", false);
        board.getColumn(1).getCell(3).setSymbol("2", false);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findCase() {
        uniqueCandidate.updateBoard();
        assert uniqueCandidate.findCase();
    }

    @Test
    void solveCase() {
        uniqueCandidate.updateBoard();
        uniqueCandidate.findCase();
        assert uniqueCandidate.solveCase();
        assert board.getRow(1).getCell(0).getSymbol().equals("2");
    }

}