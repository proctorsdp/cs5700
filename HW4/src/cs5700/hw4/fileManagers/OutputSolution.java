package cs5700.hw4.fileManagers;

import cs5700.hw4.sudoku.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OutputSolution {

    private String filename;

    public OutputSolution(String filename) {
        this.filename = filename;
    }

    public void write(String unsolvedPuzzle, boolean isValid) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(filename);
        printWriter.write(unsolvedPuzzle);
        if (isValid) {
            printWriter.write("\n\nSOLUTION:\n");
            printWriter.write(Board.getInstance().toString());
        } else {
            printWriter.write("\n\nInvalid Puzzle!");
        }
        printWriter.close();
    }
}
