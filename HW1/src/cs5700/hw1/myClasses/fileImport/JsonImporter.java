package cs5700.hw1.myClasses.fileImport;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import cs5700.hw1.myClasses.personClasses.Person;
import org.json.JSONArray;
import org.json.JSONTokener;

/**
 * Opens and parses a .json file to create new Person objects, initialize the data members, and store the object in an
 * array list of person objects. Checks to see if data exists for a given member, and fills it with an empty string if
 * no data is found in the file.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class JsonImporter extends FileImporter {

    /**
     * Opens and reads in data from a .json file. Creates a new person object for each one in the file, and initializes
     * the data members of the Person class based on the info provided in the .json file. Saves an empty string if to data
     * if found in the file. Adds each new person object to an array list of Persons.
     * @param list an array list of Persons
     * @param filename the local path/filename of the input file
     */
    @Override
    public void read(List<Person> list, String filename) {

        try {
            Reader jsonStr = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
            JSONArray collection = new JSONArray(new JSONTokener(jsonStr));

        for (int i = 0; i < collection.length(); i++) {
            Person person = new Person(
                    collection.getJSONObject(i).getInt("ObjectId"),
                    collection.getJSONObject(i).optString("StateFileNumber", ""),
                    collection.getJSONObject(i).optString("SocialSecurityNumber", ""),
                    collection.getJSONObject(i).optString("FirstName", ""),
                    collection.getJSONObject(i).optString("MiddleName",""),
                    collection.getJSONObject(i).optString("LastName",""),
                    collection.getJSONObject(i).getInt("BirthYear"),
                    collection.getJSONObject(i).getInt("BirthMonth"),
                    collection.getJSONObject(i).getInt("BirthDay"),
                    collection.getJSONObject(i).optString("Gender",""),
                    collection.getJSONObject(i).optString("NewbornScreeningNumber", ""),
                    collection.getJSONObject(i).optString("IsPartOfMultipleBirth", ""),
                    collection.getJSONObject(i).getInt("BirthOrder"),
                    collection.getJSONObject(i).optString("BirthCounty", ""),
                    collection.getJSONObject(i).optString("MotherFirstName", ""),
                    collection.getJSONObject(i).optString("MotherMiddleName", ""),
                    collection.getJSONObject(i).optString("MotherLastName", ""),
                    collection.getJSONObject(i).optString("Phone1",""),
                    collection.getJSONObject(i).optString("Phone2", "")
            );
            list.add(person);
        }

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
}
