package cs5700.hw1.myClasses.summarizeMatches;

import cs5700.hw1.myClasses.personClasses.MatchedPair;
import java.util.List;

/**
 * Creates a string that summarizes each matched pair by object ID's only
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class SumByID extends Summarize {

    /**
     * Iterates through a list of matched pairs and prints a line that contains only the object ID of each person
     * @param list array list of matched pairs
     * @return a string of each matched summarized by object ID
     */
    @Override
    public String summarize(List<MatchedPair> list) {
        for (MatchedPair pair : list) {
            summary += (pair.getPerson(1).getObjectID() + ", " + pair.getPerson(2).getObjectID() + "\n");
        }
        return summary;
    }
}
