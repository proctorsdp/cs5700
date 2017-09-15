package cs5700.hw1.myClasses.summarizeMatches;

import cs5700.hw1.myClasses.personClasses.MatchedPair;

import java.util.List;

/**
 * Abstract class the utilizes the Strategy Pattern to allow the user to select different ways of summarizing the results
 * of the match performed an a list of Persons.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public abstract class Summarize {

    /**
     * protected variable string that stores the string that will be exported to a file/console
     */
    protected String summary = "RESULTS:\n";

    /**
     * abstract method that uses the strategy method to allow the user to select various methods of summarizing results
     * @param list array list of matched pairs
     * @return a string that contains the summary of all matched pair objects
     */
    public abstract String summarize(List<MatchedPair> list);
}
