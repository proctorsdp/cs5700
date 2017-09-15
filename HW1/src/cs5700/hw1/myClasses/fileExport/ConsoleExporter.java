package cs5700.hw1.myClasses.fileExport;

import cs5700.hw1.myClasses.personClasses.MatchedPair;
import java.util.List;

/**
 * Initializes a Summarize class and prints the String returned by the class to to console.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class ConsoleExporter extends FileExporter {

    /**
     * Calls setSummaryType() to initialize a Summary class based on the user input. Prints the string returned by the
     * Summarize class to the console.
     * @param list array list of MatchedPairs
     * @param filename local path/filename for the output file
     * @param summaryType integer corresponding to a summary method
     */
    @Override
    public void toFile(List<MatchedPair> list, String filename, int summaryType) {
        setSummaryType(summaryType);
        System.out.println(this.summary.summarize(list));
    }
}
