package cs5700.hw1.testClasses;

import cs5700.hw1.myClasses.personClasses.MatchedPairCollection;
import cs5700.hw1.myClasses.personClasses.PersonCollection;


/**
 * Compares the results of each match algorithm to the expected results to ensure each algorithm is functioning as expected.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class TestMatch extends Test {

    /**
     * Tests to see if the matching algorithms return the expected results. Creates a new personCollection, and builds
     * the person list contained in the personCollection instance. Each match type is run and the size is checked to
     * ensure the correct number of matches were found. The data member isMatched for each person is reset to false
     * after each match is performed.
     */
    @Override
    public void testFunction() {
        PersonCollection personList = new PersonCollection();
        resetMatched();
        buildList(personList);
        MatchedPairCollection matchedList1 = new MatchedPairCollection();
        personList.match(matchedList1, 1);
        assert matchedList1.getSize() == 2 : "Match by Name didn't find the expected number of matches";
        resetMatched();
        MatchedPairCollection matchedList2 = new MatchedPairCollection();
        personList.match(matchedList2, 2);
        assert matchedList2.getSize() == 2 : "Match by Mother/Birth didn't find the expected number of matches";
        resetMatched();
        MatchedPairCollection matchedList3 = new MatchedPairCollection();
        personList.match(matchedList3, 3);
        assert matchedList3.getSize() == 1 : "Match by Identifiers didn't find the expected number of matches";
        System.out.println("PASSED MATCH TESTS!");
    }

    /**
     * Resets the data member isMatched of each Person object to false
     */
    private void resetMatched() {
        person1.setMatched(false);
        person2.setMatched(false);
        person3.setMatched(false);
        person4.setMatched(false);
    }
}
