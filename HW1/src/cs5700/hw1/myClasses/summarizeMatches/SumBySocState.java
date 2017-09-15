package cs5700.hw1.myClasses.summarizeMatches;

import cs5700.hw1.myClasses.personClasses.MatchedPair;
import cs5700.hw1.myClasses.personClasses.Person;
import java.util.List;

/**
 * Creates a string that summarizes each matched pair by the persons object ID, social security, and state file number
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class SumBySocState extends Summarize {

    /**
     * Iterates through a list of matched pairs and prints a line that contains only the object ID, social security,
     * and state file number of each person
     * @param list array list of matched pairs
     * @return a string of each matched summarized by object ID, social security, and state file number
     */
    @Override
    public String summarize(List<MatchedPair> list) {
        for (MatchedPair pair : list) {
            summary += "\nMatch:\n";
            for (int i = 1; i <=2; i++) {
                Person p = pair.getPerson(i);
                summary += "   ObjectID: " + p.getObjectID();
                summary += "   SocialSecurityNumber: " + p.getSocSecNum();
                summary += "   StateFileNumber: " + p.getStateFileNum();
                summary += "\n";
            }
        }
        return summary;
    }
}
