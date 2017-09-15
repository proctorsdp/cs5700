package cs5700.hw1.myClasses.matchMethods;

import cs5700.hw1.myClasses.personClasses.Person;
import java.util.Objects;

/**
 * Strategy Method of the Matcher class used to determine if two person objects share the same name.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MatchByName extends Matcher {

    /**
     * Overridden isDataSame() method. Checks to see if the two Person Objects share the same name. Persons must have
     * identical first, middle, and last names to be considered a match.
     * @param p1 person object 1
     * @param p2 person object 2
     * @return true if the person objects share the same first, middle, and last name
     */
    @Override
    protected boolean isDataSame(Person p1, Person p2) {
        return Objects.equals(p1.getFirstName(), p2.getFirstName()) &&
                Objects.equals(p1.getLastName(), p2.getLastName()) &&
                Objects.equals(p1.getMiddleName(), p2.getMiddleName());
    }
}
