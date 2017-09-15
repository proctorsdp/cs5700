package cs5700.hw1.myClasses.matchMethods;

import cs5700.hw1.myClasses.personClasses.Person;
import java.util.Objects;

/**
 * Strategy Method of the Matcher class used to determine if two person objects share the mother, or birthDate
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MatchByMother extends Matcher {

    /**
     * Calls the checkMother and checkBirth methods to see if the two person objects share information
     * @param p1 person object 1
     * @param p2 person object 2
     * @return returns true if the two person share either the same mother or birthDate
     */
    @Override
    protected boolean isDataSame(Person p1, Person p2) {
        return checkMothers(p1, p2) || checkBirth(p1, p2);
    }

    /**
     * Checks to see if the person object contains information on the mother. If so, checks to see if it's identical to
     * the second person object.
     * @param p1 person object 1
     * @param p2 person object 2
     * @return true if both objects have identical information on the mother. Returns false if info is missing.
     */
    private boolean checkMothers(Person p1, Person p2) {
        if (Objects.equals(p1.getMomFirstName(), "") && Objects.equals(p1.getMomLastName(), "")) {
            return false;
        }
        return Objects.equals(p1.getMomFirstName(), p2.getMomFirstName()) &&
                Objects.equals(p1.getMomLastName(), p2.getMomLastName());
    }

    /**
     * Checks to see if both person object share the same birthDate
     * @param p1 person object 1
     * @param p2 person object 2
     * @return true if both objects have identical birthDates
     */
    private boolean checkBirth(Person p1, Person p2) {
        return Objects.equals(p1.getBirthYear(), p2.getBirthYear()) &&
                Objects.equals(p1.getBirthMonth(), p2.getBirthMonth()) &&
                Objects.equals(p1.getBirthDay(), p2.getBirthDay());
    }
}
