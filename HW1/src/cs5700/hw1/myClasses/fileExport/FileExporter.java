package cs5700.hw1.myClasses.fileExport;

import cs5700.hw1.myClasses.personClasses.MatchedPair;
import cs5700.hw1.myClasses.summarizeMatches.*;
import java.util.List;

/**
 * Abstract class the utilizes the Strategy Method to allow for multiple ways to export the data. Currently only supports
 * console output and .txt output
 *
 * @author Steven Proctor
 * @version 1.0
 */
public abstract class FileExporter {

    /**
     * An uninitialized instance of the Summarize class. Will be initialized when the file exporter class begins running.
     */
    protected Summarize summary;

    /**
     * Takes in a list of matched pairs, and local path/filename for the output file, and the summary type requested by
     * the user, and saves the data to a file of their choosing. By default the results are printed to the console if no
     * output file is given.
     * @param list an array list of matched pairs
     * @param filename a local path/filename for the output file
     * @param summaryType an integer corresponding to a summary type
     */
    public abstract void toFile(List<MatchedPair> list, String filename, int summaryType);

    /**
     * A method used by each strategy to initialize the Summarize object to the requested type of summary.
     * @param summaryType integer corresponding to a summary type
     */
    protected void setSummaryType(int summaryType) {
        if (summaryType == 2) {
            summary = new SumByID();
        }
        else if (summaryType == 3) {
            summary = new SumBySocState();
        }
        else if (summaryType == 4) {
            summary = new SumByMotherBirth();
        }
        else {
            summary = new SumByNameBirth();
        }
    }
}
