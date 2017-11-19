package cs5700.hw4;

import cs5700.hw4.fileManagers.InputSudoku;
import cs5700.hw4.solvers.OnePossibleSolution;
import cs5700.hw4.sudoku.Board;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        InputSudoku inputSudoku = new InputSudoku("data/Puzzle-4x4-0001.txt");
        inputSudoku.readFile();
        Board board = Board.getInstance();
        board.initBoard(inputSudoku.getBoard(), inputSudoku.getSymbols());

        System.out.println(board);

        for (int i = 0; i < 4; i++) {
            OnePossibleSolution solver = new OnePossibleSolution(board.getRow(i));
        }

//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (board.isValidSymbol("" + (j + 1)) && !board.getRow(i).contains("" + (j + 1))) {
//                    for (int k = 0; k < 4; k++) {
//                        if (board.getRow(i).getCell(k).getSymbol().equals("-")) {
//                            board.getRow(i).getCell(k).setSymbol("" + (j + 1));
//                        }
//                    }
//                }
//            }
//        }

        System.out.println(board);
    }

    private static void testToString() {

    }

}
