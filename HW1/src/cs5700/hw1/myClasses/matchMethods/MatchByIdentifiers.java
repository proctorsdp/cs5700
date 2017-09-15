package cs5700.hw1.myClasses.matchMethods;

import cs5700.hw1.myClasses.personClasses.Person;
import java.util.Objects;

/**
 * Strategy Method of the Matcher class used to determine if two person objects share the same social security or state
 * file number.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MatchByIdentifiers extends Matcher {

    /**
     * Overridden isDataSame() that checks to see if two person objects have identical socialSecurity or stateFile numbers.
     * @param p1 person object 1
     * @param p2 person object 2
     * @return true if either the social security number, or the state file number of each person is identical to the other.
     */
    @Override
    protected boolean isDataSame(Person p1, Person p2) {
        boolean hasSocialNum = !Objects.equals(p1.getSocSecNum(), "");
        boolean hasStateNum = !Objects.equals(p1.getStateFileNum(), "");

        return (hasSocialNum && Objects.equals(p1.getSocSecNum(), p2.getSocSecNum())) ||
                (hasStateNum && Objects.equals(p1.getStateFileNum(), p2.getStateFileNum()));
    }
}
