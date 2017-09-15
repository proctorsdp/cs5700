package cs5700.hw1.testClasses;

import cs5700.hw1.myClasses.personClasses.MatchedPairCollection;
import cs5700.hw1.myClasses.personClasses.PersonCollection;
import cs5700.hw1.myClasses.summarizeMatches.*;


/**
 * Add a description of the class here
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class TestSummarize extends Test {

    /**
     * private instance of the Summarize class used to set summarizeByID
     */
    private Summarize idSummary = new SumByID();

    /**
     * private instance of the Summarize class used to set summarizeByName/Birth
     */
    private Summarize nameSummary = new SumByNameBirth();

    /**
     * private instance of the Summarize class used to set summarizeByMother/Birth
     */
    private Summarize motherSummary = new SumByMotherBirth();

    /**
     * private instance of the Summarize class used to set summarizeBySocialSecurity/StateFile
     */
    private Summarize socialSummary = new SumBySocState();

    /**
     * private string containing the expected results of the sumByID() method
     */
    private String idString = "RESULTS:\n195, 195\n200, 200\n";

    /**
     * private string containing the expected results of the sumByNameBirth() method
     */
    private String nameString = "RESULTS:\n\nMatch:\n" +
            "   ObjectID: 195   Name: Steven David Proctor   BirthDate: 7/25/1991\n" +
            "   ObjectID: 195   Name: Steven David Proctor   BirthDate: 7/25/1991\n" +
            "\nMatch:\n" +
            "   ObjectID: 200   Name: James Hansen Proctor   BirthDate: 9/25/1991\n" +
            "   ObjectID: 200   Name: James Hansen Proctor   BirthDate: 9/25/1991\n";

    /**
     * private string containing the expected results of the sumByMotherBirth() method
     */
    private String motherString = "RESULTS:\n\nMatch:\n" +
            "   ObjectID: 195   MothersName:      BirthDate: 7/25/1991\n" +
            "   ObjectID: 195   MothersName: Ann-Marie  Proctor   BirthDate: 7/25/1991\n" +
            "\nMatch:\n" +
            "   ObjectID: 200   MothersName: Ann-Marie  Proctor   BirthDate: 9/25/1991\n" +
            "   ObjectID: 200   MothersName: Ann-Marie  Proctor   BirthDate: 9/25/1991\n";

    /**
     * private string containing the expected results of the sumBySocState() method
     */
    private String socialString = "RESULTS:\n\nMatch:\n" +
            "   ObjectID: 195   SocialSecurityNumber: 19053805   StateFileNumber: 194055 834\n" +
            "   ObjectID: 195   SocialSecurityNumber: 19053802   StateFileNumber: 194055 832\n" +
            "\nMatch:\n" +
            "   ObjectID: 200   SocialSecurityNumber: 19053807   StateFileNumber: 194055 837\n" +
            "   ObjectID: 200   SocialSecurityNumber: 19053807   StateFileNumber: 194055 837\n";

    /**
     * Creates a matched pair collection and populates it with the matches found by matching the private Person objects
     * by name. Then summarizes the results using each summarize method, checking them against the expected results.
     */
    @Override
    public void testFunction() {
        MatchedPairCollection matchedList = new MatchedPairCollection();
        PersonCollection personList = new PersonCollection();
        buildList(personList);
        personList.match(matchedList, 1);
        assert idString.equals(idSummary.summarize(matchedList.getList())) : "Summarize By ID does not provide expected results\n";
        assert nameString.equals(nameSummary.summarize(matchedList.getList())) : "Summarize by name/birth does not provide expected results\n";
        assert motherString.equals(motherSummary.summarize(matchedList.getList())) : "Summarize by mother/birth does not provide expected results\n";
        assert socialString.equals(socialSummary.summarize(matchedList.getList())) : "Summarize by social/state does not provide expected results\n";
        System.out.println("SUMMARIZE TESTS CASES PASSED!");
    }
}
