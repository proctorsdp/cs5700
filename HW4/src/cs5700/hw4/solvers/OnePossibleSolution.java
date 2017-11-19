package cs5700.hw4.solvers;

import cs5700.hw4.sudoku.Board;
import cs5700.hw4.sudoku.Cell;
import cs5700.hw4.sudoku.Group;

import java.util.ArrayList;
import java.util.Iterator;

public class OnePossibleSolution {

    private Group group;

    private Iterator iterator;

    public OnePossibleSolution(Group group) {
        this.group = group;
        this.iterator = group.getIterator();
        solve();
    }

    private void solve() {
        if (group.numEmptyCells() > 1) { return; }

        Cell emptyCell = getEmptyCell();
        emptyCell.setSymbol(emptyCell.getPossibleSymbol(), false);

    }

    private Cell getEmptyCell() {
        while (iterator.hasNext()) {
            Cell c = (Cell) iterator.next();
            if (c.isEmpty())  {
                return c;
            }
        }
        return null;
    }

}
