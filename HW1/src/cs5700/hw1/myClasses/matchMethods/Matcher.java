package cs5700.hw1.myClasses.matchMethods;

import cs5700.hw1.myClasses.personClasses.MatchedPairCollection;
import cs5700.hw1.myClasses.personClasses.Person;
import java.util.List;

/**
 * An abstract class used to implement the Strategy Pattern and allow the matching algorithm to be initialized at runtime
 * based on user input. Match() will iterate through the list of Person objects, while isDataSame() will look for specific
 * types of matches to be stored in a MatchedPairCollection
 *
 * @author Steven Proctor
 * @version 1.0
 */
public abstract class Matcher {

    /**
     * Iterates through a list of person objects and adds them to a matchedPair array as a matchedPair object if the two
     * person objects share identical data. After a person object has been matched it's private data member isMatched is
     * marked as true, to prevent that person from being matched repeatedly to other repeated instances of the person.
     * The abstract method, isDataSame() is called to check if the two person objects share identical information. If the
     * person objects match, the addToList() method of MatchedPairCollection class is used to store the matched persons.
     * @param pairList
     * @param singleList
     */
    public void match(MatchedPairCollection pairList, List<Person> singleList) {
        for (Person person : singleList) {
            if (!person.isMatched()) {
                for (int i = singleList.indexOf(person) + 1; i < singleList.size(); i++) {
                    if (!singleList.get(i).isMatched() && isDataSame(singleList.get(i), person)) {
                        pairList.addToList(person, singleList.get(i));
                        singleList.get(i).setMatched(true);
                        person.setMatched(true);
                    }
                }
            }
        }
    }

    /**
     * Abstract method used to determine matching algorithm at runtime
     * @param p1 person object 1
     * @param p2 person object 2
     * @return true if the two person objects contain matching information
     */
    protected abstract boolean isDataSame(Person p1, Person p2);
}
