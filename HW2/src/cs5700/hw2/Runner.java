package cs5700.hw2;

public class Runner extends Athlete {
    private int bibNumber;

    public Runner(String firstName, String lastName, String gender, int age, int bibNumber) {
        super(firstName, lastName, gender, age);
        this.bibNumber = bibNumber;
    }

    public int getBibNumber() {
        return bibNumber;
    }

    public void setBibNumber(int bibNumber) {
        this.bibNumber = bibNumber;
    }
}
