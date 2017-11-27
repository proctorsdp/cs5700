package cs5700.hw4.unitTests;

import cs5700.hw4.sudoku.Cell;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell("-");
        cell.addPossibleSymbol("1");
        cell.addPossibleSymbol("2");
        cell.addPossibleSymbol("3");
        cell.addPossibleSymbol("4");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPossibleSymbol() {
        for (int i = 1; i < 5; i++) {
            String nextPossibility = cell.getPossibleSymbol();
            assert nextPossibility.equals("" + i);
            assert cell.getPossibleSymbols().size() == 4 - i;
        }
        cell.getPossibleSymbol();
    }

    @Test
    void addAllPossibleSymbols() {
        ArrayList<String> morePossibilities = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            morePossibilities.add("" + i);
        }
        cell.addAllPossibleSymbols(morePossibilities);
        assert cell.getPossibleSymbols().size() == 9;
    }

    @Test
    void removePossibility() {
        cell.removePossibility("2");
        assert !cell.getPossibleSymbols().contains("2");
        cell.removePossibility("-");
        assert !cell.getPossibleSymbols().contains("-");
    }

    @Test
    void removeAllPossibilities() {
        ArrayList<String> removePossibilities = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            removePossibilities.add("" + i);
        }
        cell.removeAllPossibilities(removePossibilities);
        assert cell.getPossibleSymbols().size() == 0;
    }

    @Test
    void emptyCell() {
        cell.setSymbol("4", true);
        cell.emptyCell();
        assert cell.getPossibleSymbols().size() == 0;
        assert cell.getSymbol().equals("-");
        assert cell.isEmpty();
        assert !cell.isGuess();
    }

    @Test
    void testToString() {
        assert cell.toString().equals("-");
    }

    @Test
    void equals() {
        Cell cellTwo = new Cell("-");
        assert !cell.equals(cellTwo);
        cell.setSymbol("2", false);
        cellTwo.setSymbol("2", true);
        assert cellTwo.equals(cell);
    }

}