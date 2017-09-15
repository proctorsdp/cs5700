package cs5700.hw1.testClasses;

import cs5700.hw1.myClasses.personClasses.*;


/**
 * Abstract class that utilizes the Strategy Pattern to implement various types of unit tests that check the results of
 * matching and summary algorithms. Contains four Person objects that match under a variety of circumstances.
 *
 * Match by Name:
 *      person1 and person2
 *      person3 and person4
 *
 * Match by Mother/Birth:
 *      person1 and person2 (by birth)
 *      person3 and person4 (by mother)
 *
 * Match by social/state number:
 *      person3 and person4
 *
 * @author Steven Proctor
 * @version 1.0
 */
public abstract class Test {
    protected Person person1 = new Person(
            195,
            "194055 834",
            "19053805",
            "Steven",
            "David",
            "Proctor",
            1991,
            7,
            25,
            "Male",
            "",
            "",
            0,
            "Utah",
            "",
            "",
            "",
            "8015035830",
            ""
    );
    protected Person person2 = new Person(
            195,
            "194055 832",
            "19053802",
            "Steven",
            "David",
            "Proctor",
            1991,
            7,
            25,
            "Male",
            "",
            "",
            0,
            "Utah",
            "Ann-Marie",
            "",
            "Proctor",
            "8015035830",
            ""
    );
    protected Person person3 = new Person(
            200,
            "194055 837",
            "19053807",
            "James",
            "Hansen",
            "Proctor",
            1991,
            9,
            25,
            "Male",
            "",
            "",
            0,
            "Utah",
            "Ann-Marie",
            "",
            "Proctor",
            "8015032956",
            ""
    );
    protected Person person4 = new Person(
            200,
            "194055 837",
            "19053807",
            "James",
            "Hansen",
            "Proctor",
            1991,
            9,
            25,
            "Male",
            "",
            "",
            0,
            "Utah",
            "Ann-Marie",
            "",
            "Proctor",
            "8015035830",
            ""
    );

    /**
     * adds each person object to an personCollection object
     * @param personList instance of a PersonCollection
     */
    protected void buildList(PersonCollection personList) {
        personList.addToList(person1);
        personList.addToList(person2);
        personList.addToList(person3);
        personList.addToList(person4);
    }

    /**
     * Abstract function that utilizes Strategy pattern to test different aspects of the program
     */
    public abstract void testFunction();
}
