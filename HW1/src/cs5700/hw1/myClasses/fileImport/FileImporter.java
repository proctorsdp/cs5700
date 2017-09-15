package cs5700.hw1.myClasses.fileImport;

import cs5700.hw1.myClasses.personClasses.Person;
import java.util.List;

/**
 * Abstract Class used to implement the Strategy Method to allow for various types of file input. Currently only supports
 * .xml and .json files
 *
 * @author Steven Proctor
 * @version 1.0
 */
public abstract class FileImporter {

    /**
     * abstract method that will read in data from a given file name, and store the newly created person objects in an array list.
     * @param list an array list of person objects
     * @param filename the local path/filename of the input file
     */
    public abstract void read(List<Person> list, String filename);
}
