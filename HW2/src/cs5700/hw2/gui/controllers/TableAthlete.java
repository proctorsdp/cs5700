package cs5700.hw2.gui.controllers;

import cs5700.hw2.application.subjects.Athlete;
import cs5700.hw2.application.tools.CalculateInfo;
import javafx.beans.property.*;
import javafx.scene.control.ProgressBar;

public class TableAthlete {
    private final IntegerProperty bibNum;
    private final StringProperty lastName;
    private final StringProperty firstName;
    private final StringProperty location;
    private final StringProperty pace;
    private final ProgressBar progressBar;

    public TableAthlete(Athlete athlete) {
        this.bibNum = new SimpleIntegerProperty(athlete.getBibNumber());
        this.lastName = new SimpleStringProperty(athlete.getLastName());
        this.firstName = new SimpleStringProperty(athlete.getFirstName());
        this.location = new SimpleStringProperty(CalculateInfo.getInstance().locationToString(athlete));
        this.pace = new SimpleStringProperty(CalculateInfo.getInstance().paceToString(athlete));
        this.progressBar = new ProgressBar(athlete.getLocationOnCourse()/CalculateInfo.getInstance().getRaceMeters());
    }


    public int getBibNum() {
        return bibNum.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getFirstName() { return firstName.get(); }

    public String getLocation() { return location.get(); }

    public StringProperty firstNameProperty() { return firstName; }

    public String getPace() { return pace.get(); }

    public StringProperty locationProperty() { return location; }

    public StringProperty paceProperty() { return pace; }

    public IntegerProperty bibNumProperty() {
        return bibNum;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ProgressBar getProgressBar() { return progressBar; }

}
