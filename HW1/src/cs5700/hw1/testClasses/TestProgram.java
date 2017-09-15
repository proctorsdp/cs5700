package cs5700.hw1.testClasses;

/**
 * Main class to run unit tests designed to check the output of various components of the MatchPersons program.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class TestProgram {

    /**
     * Runs each test unit for the program
     * @param args
     */
    public static void main(String[] args) {
        TestSummarize summaryTest = new TestSummarize();
        TestMatch matchTest = new TestMatch();
        summaryTest.testFunction();
        matchTest.testFunction();
    }
}
