package cs5700.hw1.myClasses.personClasses;

/**
 * The MatchedPair class stores two unique Person objects that have matching characteristics. Contains public methods,
 * getPerson to retrieve one of the matching person objects, as well as a toString() method that returns a string of the
 * two matching person objects, based on the selected summary type.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class MatchedPair {

    /**
     * Private Person object that contains one of two matching person objects
     */
    private Person person1;

    /**
     * Private Person object that contains the second of two matching person objects
     */
    private Person person2;

    /**
     * Default Constructor
     */
    MatchedPair() {}

    /**
     * Custom Constructor that allows to matching person objects to be initialized during creation
     * @param p1 the first of two matching person objects
     * @param p2 the second of two matching person objects
     */
    MatchedPair(Person p1, Person p2) {
        this.person1 = p1;
        this.person2 = p2;
    }

    /**
     * Returns one of the matched person objects given an int value. Default is person2.
     * @param person an integer corresponding to either person1 or person2
     * @return person 1 if int 1 is passed in, otherwise person2 is returned
     */
    public Person getPerson(int person) {
        if (person == 1) {
            return person1;
        } else {
            return person2;
        }
    }


    /**
     * Calls the toString method from the Person class and combines them in a "Matched" string.
     * @return a string of the two person objects that match
     */
    public String toString() {
        return "Match:\n" + person1.toString() + person2.toString();
    }

}
