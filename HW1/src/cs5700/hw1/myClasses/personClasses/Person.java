package cs5700.hw1.myClasses.personClasses;

/**
 * Public class Person which contains standard health care identification information.
 *
 * @author Steven Proctor
 * @version 1.0
 */
public class Person {

    /**
     * Private int that stores the ID number corresponding the person's data file.
     */
    private int objectID;

    /**
     * Private string that stores the person's state file number.
     */
    private String stateFileNum;

    /**
     * Private string that stores the person's social security number.
     */
    private String socSecNum;

    /**
     * Private string that stores the person's first name.
     */
    private String firstName;

    /**
     * Private string that stores the person's middle name.
     */
    private String middleName;

    /**
     * Private string that stores the person's last name.
     */
    private String lastName;

    /**
     * Private int that stores the year the person was born
     */
    private int birthYear;

    /**
     * Private int that stores the month the person was born
     */
    private int birthMonth;

    /**
     * Private int that stores the day the person was born
     */
    private int birthDay;

    /**
     * Private string that stores the person's gender.
     */
    private String gender;

    /**
     * Private string that stores the person's newborn screening number.
     */
    private String newbornScreenNum;

    /**
     * Private string that stores information if the person was born with twins, triplets, etc.
     */
    private String multipleBirth;

    /**
     * Private int that stores the order the person was born in the case of a multiple birth
     */
    private int birthOrder;

    /**
     * Private string that stores the county the person was born in.
     */
    private String birthCounty;

    /**
     * Private string that stores the first name of the peron's mother.
     */
    private String momFirstName;

    /**
     * Private string that stores the middle name of the person's mother.
     */
    private String momMiddleName;

    /**
     * Private string that stores the last name of the person's mother.
     */
    private String momLastName;

    /**
     * Private string that stores the person's primary phone number.
     */
    private String phone1;

    /**
     * Private string that stores the person's secondary phone number.
     */
    private String phone2;

    /**
     * Private boolean that contains true if the person has already been matched to another record.
     */
    private boolean isMatched = false;

    /**
     * Default constructor
     */
    public Person() { }

    /**
     * Constructor that allows all data members to be initialized at creation
     *
     * @param objectID
     * @param stateFileNum
     * @param socSecNum
     * @param firstN
     * @param middleN
     * @param lastN
     * @param birthYear
     * @param birthMonth
     * @param birthDay
     * @param gender
     * @param newbornScrnNum
     * @param multipleBirth
     * @param birthOrder
     * @param birthCounty
     * @param momFirstN
     * @param momMiddleN
     * @param momLastN
     * @param phone1
     * @param phone2
     */
    public Person
            (
            int objectID,
            String stateFileNum,
            String socSecNum,
            String firstN,
            String middleN,
            String lastN,
            int birthYear,
            int birthMonth,
            int birthDay,
            String gender,
            String newbornScrnNum,
            String multipleBirth,
            int birthOrder,
            String birthCounty,
            String momFirstN,
            String momMiddleN,
            String momLastN,
            String phone1,
            String phone2
            )
    {
        this.objectID = objectID;
        this.stateFileNum = stateFileNum;
        this.socSecNum = socSecNum;
        this.firstName = firstN;
        this.middleName = middleN;
        this.lastName = lastN;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.gender = gender;
        this.newbornScreenNum = newbornScrnNum;
        this.multipleBirth = multipleBirth;
        this.birthOrder = birthOrder;
        this.birthCounty = birthCounty;
        this.momFirstName = momFirstN;
        this.momMiddleName = momMiddleN;
        this.momLastName = momLastN;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    /**
     * Public getter method to obtain the object ID
     * @return objectID
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * Public setter method for objectID
     * @param objectID
     */
    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    /**
     * Public getter method to obtain person's state file num
     * @return statFileNum
     */
    public String getStateFileNum() {
        return stateFileNum;
    }

    /**
     * Public setter method for objectID
     * @param stateFileNum
     */
    public void setStateFileNum(String stateFileNum) {
        this.stateFileNum = stateFileNum;
    }

    /**
     * Public getter method to obtain the person's Social Security Number
     * @return socSecNum
     */
    public String getSocSecNum() {
        return socSecNum;
    }

    /**
     * Public setter method for socSecNum
     * @param socSecNum
     */
    public void setSocSecNum(String socSecNum) {
        this.socSecNum = socSecNum;
    }

    /**
     * Public getter method to obtain the peron's first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Public setter method for firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Public getter method to obtain the peron's middle name
     * @return middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Public setter method for middleName
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Public getter method to obtain the peron's last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Public setter method for lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Public getter method to obtain the year the person was born
     * @return birthYear
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Public setter method for birthYear
     * @param birthYear
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * Public getter method to obtain the month the person was born
     * @return birthMonth
     */
    public int getBirthMonth() {
        return birthMonth;
    }

    /**
     * Public setter method for birthMonth
     * @param birthMonth
     */
    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    /**
     * Public getter method to obtain day the person was born
     * @return birthDay
     */
    public int getBirthDay() {
        return birthDay;
    }

    /**
     * Public setter method for birthDay
     * @param birthDay
     */
    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * Public getter method to obtain the peron's gender
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * Public setter method for gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Public getter method to obtain the newborn screening number
     * @return newbornScreenNum
     */
    public String getNewbornScreenNum() {
        return newbornScreenNum;
    }

    /**
     * Public setter method for newbornScreenNum
     * @param newbornScreenNum
     */
    public void setNewbornScreenNum(String newbornScreenNum) {
        newbornScreenNum = newbornScreenNum;
    }

    /**
     * Public getter method to obtain info about multiple births
     * @return multipleBirth
     */
    public String getMultipleBirth() {
        return multipleBirth;
    }

    /**
     * Public setter method for multipleBirth
     * @param multipleBirth
     */
    public void setMultipleBirth(String multipleBirth) {
        this.multipleBirth = multipleBirth;
    }

    /**
     * Public getter method to obtain the order the person was born
     * @return birthOrder
     */
    public int getBirthOrder() {
        return birthOrder;
    }

    /**
     * Public setter method for birthOrder
     * @param birthOrder
     */
    public void setBirthOrder(int birthOrder) {
        this.birthOrder = birthOrder;
    }

    /**
     * Public getter method to obtain the birthCounty
     * @return birthCounty
     */
    public String getBirthCounty() {
        return birthCounty;
    }

    /**
     * Public setter method for birthCounty
     * @param birthCounty
     */
    public void setBirthCounty(String birthCounty) {
        this.birthCounty = birthCounty;
    }

    /**
     * Public getter method to obtain the mother's first name
     * @return momFirstName
     */
    public String getMomFirstName() {
        return momFirstName;
    }

    /**
     * Public setter method for momFirstName
     * @param momFirstName
     */
    public void setMomFirstName(String momFirstName) {
        this.momFirstName = momFirstName;
    }

    /**
     * Public getter method to obtain the mother's middle name
     * @return momMiddleName
     */
    public String getMomMiddleName() {
        return momMiddleName;
    }

    /**
     * Public setter method for momMiddleName
     * @param momMiddleName
     */
    public void setMomMiddleName(String momMiddleName) {
        this.momMiddleName = momMiddleName;
    }

    /**
     * Public getter method to obtain the mother's last name
     * @return momLastName
     */
    public String getMomLastName() {
        return momLastName;
    }

    /**
     * Public setter method for momLastName
     * @param momLastName
     */
    public void setMomLastName(String momLastName) {
        this.momLastName = momLastName;
    }

    /**
     * Public getter method to obtain the phone1 data member
     * @return phone1
     */
    public String getPhone1() {
        return phone1;
    }

    /**
     * Public setter method for phone1
     * @param phone1
     */
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    /**
     * Public getter method to obtain phone2 data member
     * @return phone2
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     * Public setter method for phone2
     * @param phone2
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     * Public getter method for isMatched
     * @return isMatched
     */
    public boolean isMatched() { return isMatched; }

    /**
     * Public setter method for isMatched
     * @param x true or false - if record has been matched to another record
     */
    public void setMatched(boolean x) { this.isMatched = x; }
}
