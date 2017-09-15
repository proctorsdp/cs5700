package cs5700.hw1.myClasses.fileImport;

import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import cs5700.hw1.myClasses.personClasses.Person;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;

/**
 * Opens and parses a .xml file to create new Person objects, initialize the data members, and store the object in an
 * array list of person objects. Checks to see if data exists for a given member, and fills it with an empty string if
 * no data is found in the file.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class XmlImporter extends FileImporter {

    /**
     * Opens an .xml file and reads in person objects one at a time. Creates a new Person object and initializes data
     * members with available information. Adds new person objects to the arrayList of persons that was passed in.
     * @param list an arrayList of person objects
     * @param filename local path and filename for the input file
     */
    @Override
    public void read(List<Person> list, String filename) {

        try {
            File xmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Person");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Person person = new Person(
                            Integer.parseInt(checkForData("ObjectId", element)),
                            checkForData("StateFileName", element),
                            checkForData("SocialSecurityNumber", element),
                            checkForData("FirstName", element),
                            checkForData("MiddleName", element),
                            checkForData("LastName", element),
                            Integer.parseInt(checkForData("BirthYear", element)),
                            Integer.parseInt(checkForData("BirthMonth", element)),
                            Integer.parseInt(checkForData("BirthDay", element)),
                            checkForData("Gender", element),
                            checkForData("NewbornScreeningNumber", element),
                            checkForData("IsPartOfMultipleBirth", element),
                            Integer.parseInt(checkForData("BirthOrder", element)),
                            checkForData("BirthCounty", element),
                            checkForData("MotherFirstName", element),
                            checkForData("MotherMiddleName", element),
                            checkForData("MotherLastName", element),
                            checkForData("Phone1", element),
                            checkForData("Phone2", element)
                    );

                    list.add(person);
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }


    /**
     * Checks to see if the data at a given element is empty. If the data is empty an empty string is returned.
     * @param name name of the data member
     * @param element the element that is currently being parsed
     * @return the data at the given element if it exists, otherwise return an empty string
     */
    private String checkForData(String name, Element element) {
        if (element.getElementsByTagName(name).getLength() > 0) {
            return element.getElementsByTagName(name).item(0).getTextContent();
        } else { return ""; }
    }
}
