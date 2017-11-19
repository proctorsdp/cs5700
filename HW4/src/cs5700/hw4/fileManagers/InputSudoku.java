package cs5700.hw4.fileManagers;

import cs5700.hw4.sudoku.Cell;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InputSudoku {

    private int size;
    private Scanner scanner;
    private ArrayList<String> symbols;
    private ArrayList<ArrayList<Cell>> board;

    public InputSudoku(String fileName) throws IOException {
        scanner = new Scanner(new File(fileName));

        size = Integer.parseInt(scanner.next());
        symbols = new ArrayList<>();
        board = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            board.add(new ArrayList<>());
        }
    }

    public void readFile() throws IOException {
        for (int i = 0; i < size; i++) {
            symbols.add(scanner.next());
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.get(j).add(i, new Cell(scanner.next()));
            }
        }
    }

    public ArrayList<ArrayList<Cell>> getBoard() {
        return board;
    }

    public ArrayList<String> getSymbols() {
        return symbols;
    }
}
