package cs5700.hw4.unitTests;

import cs5700.hw4.solvers.SoleCandidate;
import cs5700.hw4.sudoku.Board;
import cs5700.hw4.sudoku.Cell;
import cs5700.hw4.sudoku.Row;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class SoleCandidateTest {

    private Board board;

    private SoleCandidate soleCandidate;

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
        soleCandidate = SoleCandidate.getInstance();

        Row row_0 = board.getRow(0);
        for (int i = 1; i < 4; i++) {
            row_0.getCell(0).setSymbol("" + i, false);
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findCase() {
        soleCandidate.updateBoard();
        assert soleCandidate.findCase();
    }

    @Test
    void solveCase() {
        soleCandidate.updateBoard();
        soleCandidate.findCase();
        assert soleCandidate.solveCase();
        assert board.getRow(0).getCell(3).getSymbol().equals("4");
    }

}