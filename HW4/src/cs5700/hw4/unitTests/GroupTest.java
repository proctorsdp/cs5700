package cs5700.hw4.unitTests;

import cs5700.hw4.sudoku.Board;
import cs5700.hw4.sudoku.Cell;
import cs5700.hw4.sudoku.Row;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    private Row group;

    @BeforeEach
    void setUp() {
        Board board = Board.getInstance();

        LinkedHashSet<Cell> rowSymbols = new LinkedHashSet<>();
        LinkedHashSet<Cell> empty = new LinkedHashSet<>();
        LinkedHashSet<String> symbols = new LinkedHashSet<>();
        LinkedHashSet[] emptyGroups = new LinkedHashSet[9];

        for (int i = 1; i < 10; i++) {
            rowSymbols.add(new Cell("" + i));
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

        group = new Row(rowSymbols, 0);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void contains() {
        assert group.contains("1");
        assert !group.contains("10");
    }

    @Test
    void updatePossibilities() {
        ArrayList<String> possibilities = new ArrayList<>();
        for (int i = 0; i < 9; i += 3) {
            group.getCell(i).emptyCell();
            possibilities.add("" + i);
        }
        group.updatePossibilities();
        for (int i = 0; i < 9; i += 3) {
            assert group.getCell(i).getPossibleSymbols().size() == 3;
            assert group.getCell(i).getPossibleSymbols().equals(possibilities);
        }
    }

    @Test
    void getPossibleSymbol() {
        assert group.getPossibleSymbols().size() == 0;
        group.getPossibleSymbol();
        assert group.getPossibleSymbols().size() == 0;
        ArrayList<String> possiblitities = new ArrayList<>();
        for (int i = 0; i < 9; i += 2) {
            group.getCell(i).emptyCell();
            possiblitities.add("" + i);
        }
        for (int i = 0; i < 4; i++) {
            assert group.getPossibleSymbol().equals(possiblitities.get(i));
            assert group.getPossibleSymbols().size() == 3 - i;
        }
    }

    @Test
    void getCurrentSymbols() {
        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<String> toRemove = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            symbols.add("" + i);
        }
        assert group.getCurrentSymbols().equals(symbols);
        for (int i = 0; i < 9; i += 2) {
            group.getCell(i).emptyCell();
            toRemove.add("" + i);
        }
        symbols.removeAll(toRemove);
        assert group.getCurrentSymbols().equals(symbols);
    }

    @Test
    void testToString() {
        String output = "1 2 3 4 5 6 7 8 9";
        assert group.toString().equals(output);
    }

}