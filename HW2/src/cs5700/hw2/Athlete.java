package cs5700.hw2;

import java.time.LocalDateTime;

public abstract class Athlete {
    private String firstName;
    private String lastName;
    private LocalDateTime timeStamp;
    private LocalDateTime officialStopTime;
    private boolean isNotStarted = false;
    private boolean isNotFinished = false;
    private boolean quitRace = false;
    private LocalDateTime officialStartTime;
    private String gender;
    private int age;
    private double locationOnCourse;

    public Athlete(String firstName, String lastName, String gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LocalDateTime getOfficialStopTime() {
        return officialStopTime;
    }

    public void setOfficialStopTime(LocalDateTime officialStopTime) {
        this.officialStopTime = officialStopTime;
    }

    public boolean isNotStarted() {
        return isNotStarted;
    }

    public void setNotStarted(boolean notStarted) {
        isNotStarted = notStarted;
    }

    public boolean isNotFinished() {
        return isNotFinished;
    }

    public void setNotFinished(boolean notFinished) {
        isNotFinished = notFinished;
    }

    public boolean isQuitRace() {
        return quitRace;
    }

    public void setQuitRace(boolean quitRace) {
        this.quitRace = quitRace;
    }

    public LocalDateTime getOfficialStartTime() {
        return officialStartTime;
    }

    public void setOfficialStartTime(LocalDateTime officialStartTime) {
        this.officialStartTime = officialStartTime;
    }

    public double getLocationOnCourse() {
        return locationOnCourse;
    }

    public void setLocationOnCourse(double locationOnCourse) {
        this.locationOnCourse = locationOnCourse;
    }
}
