package cs5700.hw4.unitTests;

import cs5700.hw4.sudoku.Board;
import cs5700.hw4.sudoku.Cell;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.getInstance();

        LinkedHashSet<Cell> empty = new LinkedHashSet<>();
        LinkedHashSet<String> symbols = new LinkedHashSet<>();
        LinkedHashSet[] emptyGroups = new LinkedHashSet[9];

        for (int i = 1; i < 10; i++) {
            symbols.add("" + i);
            empty.add(new Cell("-"));
        }

        for (int j = 0; j < 9; j++) {
            emptyGroups[j] = empty;
        }

        LinkedHashSet[][] blockGroup = new LinkedHashSet[3][3];
        for (int i = 0 ; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blockGroup[i][j] = empty;
            }
        }

        board.initBoard(9, symbols, emptyGroups, emptyGroups, blockGroup);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBlockIndex() {
        assert board.getBlockIndex(8, 8) == 8;
        assert board.getBlockIndex(5, 2) == 1;
        assert board.getBlockIndex(0, 6) == 6;
        assert board.getBlockIndex(4, 4) == 4;
    }

    @Test
    void testToString() {
        String test = "" +
                "- - - - - - - - -\n" +
                "- - - - - - - - -\n" +
                "- - - - - - - - -\n" +
                "- - - - - - - - -\n" +
                "- - - - - - - - -\n" +
                "- - - - - - - - -\n" +
                "- - - - - - - - -\n" +
                "- - - - - - - - -\n" +
                "- - - - - - - - -\n";
        assert board.toString().equals(test);
    }

}