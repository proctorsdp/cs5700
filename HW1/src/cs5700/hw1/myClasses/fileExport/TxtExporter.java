package cs5700.hw1.myClasses.fileExport;

import cs5700.hw1.myClasses.personClasses.MatchedPair;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Creates .txt file, initializes a Summarize class, and prints the string returned by the Summarize class to the .txt file
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class TxtExporter extends FileExporter {

    /**
     * Creates a .txt file based on the filename provided by the user. Initializes a Summarize class using setSummaryType().
     * Prints the string returned by the Summarize class to the .txt file.
     * @param list array list of MatchedPairs
     * @param filename local path/filename of the output file
     * @param summaryType integer corresponding to the summary method
     */
    @Override
    public void toFile(List<MatchedPair> list, String filename, int summaryType) {
        try {
            FileWriter writer = new FileWriter(filename, false);
            PrintWriter printer = new PrintWriter(writer);
            setSummaryType(summaryType);

            printer.println(this.summary.summarize(list));
            printer.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
