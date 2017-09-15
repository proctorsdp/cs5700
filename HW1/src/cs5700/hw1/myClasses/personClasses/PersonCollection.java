package cs5700.hw1.myClasses.personClasses;

import cs5700.hw1.myClasses.fileExport.ConsoleExporter;
import cs5700.hw1.myClasses.fileExport.FileExporter;
import cs5700.hw1.myClasses.fileImport.*;
import cs5700.hw1.myClasses.matchMethods.*;
import cs5700.hw1.myClasses.fileExport.TxtExporter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Person Collection class stores all the person objects read in from a compatible file using the read() method,
 * and saves each person object in an Array List called personList. Also stores the instances of the FileImporter,
 * FileExporter, and Matcher classes to use in operations. PersonCollection also contains methods to match() the records
 * contained in personList and saved them to a given MatchedCollection list, and write() to a the matched record to a
 * compatible data file.
 *
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class PersonCollection {

    /**
     * Private list of Persons that will store all the person objects from the data files
     */
    private List<Person> personList = new ArrayList<>();

    /**
     * Private instance of class FileImporter that will be initialized based on input file type
     */
    private FileImporter fileImporter;

    /**
     * Private instance of class FileExporter that will be initialized based on output file type
     */
    private FileExporter fileExporter;

    /**
     * Private instance of class Matcher that will be initialized based on desired match type
     */
    private Matcher matcher;

    /**
     * Calls getInputType() to initialize fileImporter. Calls read() method from the fileImporter class.
     * @param filePath local filepath for input data
     */
    public void read(String filePath) {
        getInputType(filePath);
        fileImporter.read(personList, filePath);
    }

    /**
     * Calls getExportType() to initialize fileExporter. Calls toFile() method from fileExporter class.
     * @param list the collection of matched pairs to be exported to a file/console
     * @param filepath the local filepath for the output file
     * @param summaryType the integer value that corresponds to a summary type
     */
    public void write(MatchedPairCollection list, String filepath, int summaryType) {
        getExportType(filepath);
        fileExporter.toFile(list.getList(), filepath, summaryType);
    }

    /**
     * Calls getMatchType() to initialize matcher. Calls match() method from Matcher class.
     * @param collection the collection of matched pairs the the matches will be saved to
     * @param type the integer value that corresponds to a match type
     */
    public void match(MatchedPairCollection collection, int type) {
        getMatchType(type);
        matcher.match(collection, this.personList);
    }

    /**
     * Determines what type of file the input data is coming from, and creates an instance of the appropriate class.
     * Currently only supports .json and .xml
     * @param filePath the local file path/name for the input data file
     */
    private void getInputType(String filePath) {
        int index = filePath.lastIndexOf(".");
        String fileType = filePath.substring(index);

        if (Objects.equals(fileType, ".json")) {
            fileImporter = new JsonImporter();
        }
        else if (Objects.equals(fileType, ".xml")) {
            fileImporter = new XmlImporter();
        }
        else {
            System.err.println("Incompatible File Type\n" +
                    "Please use a different file");
        }
    }

    /**
     * Determines what type of file the ouput data will be saved to, and creates an instance of the appropriate class.
     * Currently only supports .txt and console output
     * @param filePath local file path/name for the output data file
     */
    private void getExportType(String filePath) {
        if (!filePath.contains(".")) {
            fileExporter = new ConsoleExporter();
        }
        else {
            int index = filePath.lastIndexOf(".");
            String fileType = filePath.substring(index);

            if (Objects.equals(fileType, ".txt")) {
                fileExporter = new TxtExporter();
            } else {
                System.err.println("Incompatible File Type\n" +
                        "Please use a different file");
            }
        }
    }

    /**
     * Creates an instance of the match type class based on the user input
     * @param type integer value that corresponds to a specific match type
     */
    private void getMatchType(int type) {
        if (type == 1) {
            matcher = new MatchByName();
        }
        else if (type == 2) {
            matcher = new MatchByMother();
        }
        else if (type == 3) {
            matcher = new MatchByIdentifiers();
        }
        else {
            System.err.println("Invalid Match Selection\n" +
                    "Please select a number 1-3");
        }
    }

    /**
     * Returns the current size of the personCollection list
     * @return data member personList
     */
    public int getSize() {
        return personList.size();
    }

    /**
     * Adds a new instance of a Person to the personCollection list.
     * @param p the new Person object to be added to the personList
     */
    public void addToList(Person p) { personList.add(p); }
}
