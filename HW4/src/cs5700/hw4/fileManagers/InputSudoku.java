package cs5700.hw4.fileManagers;

import cs5700.hw4.sudoku.Board;
import cs5700.hw4.sudoku.Cell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class InputSudoku {

    private int size;
    private int sqrtSize;
    private Scanner scanner;
    private String fileName;
    private boolean isValid = false;

    private LinkedHashSet[] rowSet;
    private LinkedHashSet<String> symbolSet;
    private LinkedHashSet[] colSet;
    private LinkedHashSet[][] blockSet;

    public InputSudoku(String fileName) throws IOException {
        this.fileName = fileName;
        scanner = new Scanner(new File(fileName));

        if (scanner.hasNextInt()) {
            size = scanner.nextInt();
            sqrtSize = (int) Math.sqrt(size);
            scanner.nextLine();
        }

        symbolSet = new LinkedHashSet<>();
        rowSet = new LinkedHashSet[size];
        colSet = new LinkedHashSet[size];
        blockSet = new LinkedHashSet[sqrtSize][sqrtSize];

        for (int i = 0; i < size; i++) {
            rowSet[i] = new LinkedHashSet<Cell>();
            colSet[i] = new LinkedHashSet<Cell>();
        }

        for (int i = 0; i < sqrtSize; i++) {
            for (int j = 0; j < sqrtSize; j++) {
                blockSet[i][j] = new LinkedHashSet<Cell>();
            }
        }
    }

    public boolean readFile() throws IOException {
        String pattern = "([\\w\\d\\-]\\s?){" + size + "}";
        String line = scanner.nextLine();
        if (!line.matches(pattern)) {
            return false;
        }

        Collections.addAll(symbolSet, line.split(" "));
        if (symbolSet.size() != size) {
            return false;
        }

        int row = 0;
        while (scanner.hasNextLine() && scanner.hasNext()) {
            line = scanner.nextLine();
            if (!line.matches(pattern) || row >= size) {
                return false;
            }

            String[] cells = line.split(" ");
            int col = 0;
            for (String s : cells) {
                if (!symbolSet.contains(s) && !s.equals("-")) {
                    return false;
                }
                Cell c = new Cell(s);
                blockSet[col / sqrtSize][row / sqrtSize].add(c);
                rowSet[row].add(c);
                colSet[col++].add(c);
            }
            row++;
        }
        scanner.close();
        return checkSize();
    }

    private boolean checkSize() {
        for (int i = 0; i < size; i++) {
            if (rowSet[i].size() != size || colSet[i].size() != size) {
                return false;
            }
        }

        for (int i = 0; i < sqrtSize; i++) {
            for (int j = 0; j < sqrtSize; j++) {
                if (blockSet[i][j].size() != size) {
                    return false;
                }
            }
        }

        isValid = true;
        return true;
    }

    public void createBoard() {
        Board.getInstance().initBoard(size, symbolSet, rowSet, colSet, blockSet);
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public String toString() {
        Scanner scanner = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
