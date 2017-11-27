package cs5700.hw4.unitTests;


import cs5700.hw4.fileManagers.InputSudoku;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



class InputSudokuTest {

    private Scanner filenames;

    private String fileHeader = "data/input/";

    @BeforeEach
    void setUp() throws FileNotFoundException {
        filenames = new Scanner(new File("data/test/filenames.txt"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readFile() throws IOException {
        while (filenames.hasNextLine()) {
            String file = fileHeader + filenames.next();
            InputSudoku test = new InputSudoku(file);
            test.readFile();
            String type = filenames.next();
            switch (type) {
                case "valid":
                    assert test.readFile();
                    break;
                case "invalid":
                    assert !test.readFile();
                    break;
                default:
                    assert false;
                    break;
            }
        }
    }

    @Test
    void testToString() throws IOException {
        while (filenames.hasNextLine()) {
            String file = filenames.next();
            InputSudoku test = new InputSudoku(fileHeader + file);
            test.readFile();
            filenames.next();
            File puzzle = new File(file);
            assert test.toString().equals(puzzle.toString());
        }
    }

}