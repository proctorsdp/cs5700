package cs5700.hw1.myClasses.personClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * MatchedPairCollection contains an array list of matched pair objects. Methods include addToList() which creates a
 * matchedPair object and stores it in the pairList array, and getSize() and getList() which return the size of the
 * pairList and the pairList respectively.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MatchedPairCollection {

    /**
     * Private Array List of Matched Pair objects
     */
    private List<MatchedPair> pairList = new ArrayList<>();

    /**
     * Adds to person objects to a Matched Pair object that is then added to the pairList data member
     * @param p1 person object 1
     * @param p2 person object 2
     */
    public void addToList(Person p1, Person p2) {
        MatchedPair pair = new MatchedPair(p1, p2);
        this.pairList.add(pair);
    }

    /**
     * Returns the current size of the pairList
     * @return size of the pairlist
     */
    public int getSize() {
        return pairList.size();
    }

    /**
     * Returns the pairList data member
     * @return pairList
     */
    public List<MatchedPair> getList() { return pairList; }
}
