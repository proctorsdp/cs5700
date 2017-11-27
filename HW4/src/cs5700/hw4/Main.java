package cs5700.hw4;

import cs5700.hw4.fileManagers.InputSudoku;
import cs5700.hw4.fileManagers.OutputSolution;
import cs5700.hw4.solvers.GuessSymbol;
import cs5700.hw4.solvers.SoleCandidate;
import cs5700.hw4.solvers.UniqueCandidate;
import cs5700.hw4.sudoku.Board;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // 4x4 0902     Multiple    Incorrect Solution
        // 4x4 0904     Multiple    Solved one
        // 9x9 0204     Single      Incorrect Solution
        // 9x9 0205     Single      Incorrect Solution
        // 9x9 0301     Single      Incorrect Solution
        // 9x9 0401     Single      Incorrect Solution
        // 16x16 0002   Single      Incorrect Solution
        // 16x16 0201   Single      Incorrect Solution
        // 16x16 0301   Single      Incorrect Solution
        // 16x16 0401   Single      Incorrect Solution
        // 16x16 0901   Multiple    Incorrect Solution
        // 16x16 0902   Multiple    Incorrect Solution
        // 25x25 0901   Multiple    Error


        String filename = "data/input/Puzzle-9x9-0001.txt";

        InputSudoku inputSudoku = new InputSudoku(filename);
        if (!inputSudoku.readFile()) {
            System.out.println("Invalid Puzzle!");
            String outputFile = filename.replace(".txt", "-Solution.txt");
            outputFile = outputFile.replace("input", "output");
            OutputSolution outputSolution = new OutputSolution(outputFile);
            outputSolution.write(inputSudoku.toString(), inputSudoku.isValid());
            return;
        }
        System.out.println(inputSudoku);

        inputSudoku.createBoard();
        Board board = Board.getInstance();

        SoleCandidate soleCandidate = SoleCandidate.getInstance();
        UniqueCandidate uniqueCandidate = UniqueCandidate.getInstance();
        GuessSymbol guessSymbol = GuessSymbol.getInstance();

        boolean noSolution = false;
        while (!noSolution && !board.isSolved()) {
            noSolution = true;
            while (soleCandidate.solve()) {
                noSolution = false;
                System.out.println("Sole Candidate:");
                System.out.println(board);
            }

            while (uniqueCandidate.solve()) {
                noSolution = false;
                System.out.println("Unique Candidate:");
                System.out.println(board);
            }

            if (noSolution) {
                noSolution = !guessSymbol.solve();
                System.out.println("Guess Symbol:");
                System.out.println(board);
            }
        }

        System.out.println("SOLVED");
        System.out.println(board);

        String outputFile = filename.replace(".txt", "-Solution.txt");
        outputFile = outputFile.replace("input", "output");
        OutputSolution outputSolution = new OutputSolution(outputFile);
        outputSolution.write(inputSudoku.toString(), inputSudoku.isValid());
    }

}
