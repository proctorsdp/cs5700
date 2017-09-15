package cs5700.hw1;

import cs5700.hw1.myClasses.personClasses.MatchedPairCollection;
import cs5700.hw1.myClasses.personClasses.PersonCollection;
import java.io.File;

/**
 * This class obtains command line arguments and determines whether or not the input is valid. Stores relevant data for
 * file input/output as well as match and summary types. Initializes the relevant Person classes and calls read, match,
 * and write methods for the data.
 *
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class Main {

    /**
     * Constant int used to store the current number of matching methods available.
     */
    private static int NUM_MATCH_METHODS = 3;

    /**
     * Constant int used to stor the current number of summary methods available.
     */
    private static int NUM_SUMMARY_METHODS = 4;

    /**
     * Private int that stores the command line argument corresponding to a specific match method.
     */
    private static int matchType;

    /**
     * Private int that stores the optional argument corresponding to a specific summary method.
     * Defaulted to type 0.
     */
    private static int summaryType = 0;

    /**
     * Private String that stores the file name from the argument that data is being read from.
     */
    private static String inputFileName;

    /**
     * Private String that stores the optional file name from the argument that data is saved to.
     */
    private static String outputFileName = "";

    /**
     * Private String that contains the directory where the input data files are saved.
     */
    private static String inputDirectory = "data/input/";

    /**
     * Private String that contains the directory where the output data files are saved to.
     */
    private static String outputDirectory = "data/output/";

    /**
     * Private boolean that contains true if the matched pairs are to printed to the console.
     * Defaulted to true. Overridden if user inputs an output file name in the argument.
     */
    private static boolean useConsoleOutput = true;


    /**
     * Calls the checkArguments method.
     * Creates new instances of PersonCollection and MatchedPersonCollection.
     * Calls the read method of the PersonCollection class
     * Calls the match method of the PersonCollection class
     * Calls the toFile method of the PersonCollection class
     *
     * @param args user arguments used to obtain match type, input file, output file, and summary type
     *             [0] = match type - integer values 1-3
     *                      1: Match by Name
     *                      2: Match by Mother's Name and BirthDate
     *                      3: Match by SocialSecurity and StateFile
     *             [1] = input file name - must be saved in the local data/input directory
     *             [2] = (Optional) output file name - will be saved in data/output directory. No entry results in console output
     *             [3] = (Optional) summary type - integer values 1-4
     *                      1: Summarize by ID, Name, and BirthDate (Default)
     *                      2: Summarize by ID only
     *                      3: Summarize by ID, Mother's Name, and BirthDate
     *                      4: Summarize by ID, SocialSecurity, StateFile
     */
    public static void main(String[] args) {
        System.out.println(
                "RECORD MATCHER START\n" +
                "====================================================================================\n"
        );

        if (!checkArguments(args)) { return; }
        PersonCollection people = new PersonCollection();
        MatchedPairCollection matchedPeople = new MatchedPairCollection();

        System.out.println("Reading data from input file...");
        people.read(inputDirectory + inputFileName);
        if (people.getSize() < 1) {
            System.out.println("File is empty. No data found.\n");
        }
        else {
            System.out.println("Data read successfully. " + people.getSize() + " Records imported.\n");
        }

        System.out.println("Using algorithm " + matchType + " to identify matching records...");
        people.match(matchedPeople, matchType);
        if (matchedPeople.getSize() < 1) {
            System.out.println("No matching records found.\n");
        }
        else {
            System.out.println("Record matching complete. " + matchedPeople.getSize() + " Matches identified.\n");
        }

        if (!useConsoleOutput) {
            System.out.println("Saving matching record ID's to: " + outputFileName + "...");
        }
        people.write(matchedPeople, outputDirectory + outputFileName, summaryType);
        if (!useConsoleOutput) {
            System.out.println("File saved in: " + outputDirectory);
        }

        System.out.println("\n" +
                "====================================================================================\n" +
                "PROGRAM COMPLETE"
        );
    }

    /**
     * Test the command line arguments to ensure that the input values are valid
     *      [0] = must be integer 1-3
     *      [1] = must be a a .JSON or .XML file saved in the local data/input directory
     *      [2] = (optional) output file name (Currently only supports .txt
     *      [3] = (optional) must be an integer 1-4
     *
     * @param argv the command line arguments that were passed into main()
     * @return true if the command line argument pass validity tests
     */
    private static boolean checkArguments(String[] argv) {
        if (argv.length < 2) {
            System.err.println("Not enough arguments\n\n Please run program again with the following arguments:\n " +
                    "1: The type of match you are trying to preform (integer values 1-" + NUM_MATCH_METHODS + ")\n " +
                    "2: The name of the file you are trying to import data from\n " +
                    "3: The name of the file you are trying to save the results to (OPTIONAL)\n " +
                    "4: The type of summary you want exported (integer values 1-" + NUM_SUMMARY_METHODS + ") (OPTIONAL)\n");
            return false;
        }

        if (!isInt(argv[0])) {
            System.err.println("Invalid entry for match method -- only accepts integers\n\n" +
                    "Please run program again and enter an integer that corresponds to your desired match method");
            return false;
        }

        matchType = Integer.parseInt(argv[0]);
        inputFileName = argv[1];
        String userDirectory = System.getProperty("user.dir");

        if (!isFile(inputDirectory + inputFileName)) {
            System.err.println("File not found\n\n" +
                            inputFileName + " not found in directory: " + inputDirectory + "\n" +
                    "Ensure that file exists in the listed directory and run program again");
            return false;
        }

        if (argv.length > 2) {
            if (isInt(argv[2])) {
                summaryType = Integer.parseInt(argv[2]);
            }
            else {
                outputFileName = argv[2];
                useConsoleOutput = false;
                summaryType = 2;
            }
            if (argv.length > 3 && isInt(argv[3])) {
                summaryType = Integer.parseInt(argv[3]);
            }
        }

        if ((matchType <= 0 || matchType > NUM_MATCH_METHODS) || (summaryType < 0 || summaryType > NUM_SUMMARY_METHODS)) {
            System.err.println("Invalid entry for match or summary method\n\n" +
                    "Match Method accepts integers 1-" + NUM_MATCH_METHODS +
                    "\nSummary Method accepts integers 1-" + NUM_SUMMARY_METHODS +
                    "\nPlease run program again and enter an the appropriate integer");
        }

        return true;
    }


    /**
     * Checks to see if a string element is an integer
     *
     * @param s - a single string element that should contain an int
     * @return true if the String is an integer
     */
    private static boolean isInt(String s) {
        try {
            int test = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Checks to see if a file exists in a given directory
     *
     * @param path A string containing the path to directory which stores the input file
     * @return true if the file exists in the given directory
     */
    private static boolean isFile(String path) {
        return new File(path).isFile();
    }
}