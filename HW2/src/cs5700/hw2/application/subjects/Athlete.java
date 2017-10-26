package cs5700.hw2.application.subjects;

import cs5700.hw2.application.observers.IAthleteObserver;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Athlete implements IAthleteObservable {
    private ArrayList<IAthleteObserver> observerList = new ArrayList<>();
    private String firstName;
    private String lastName;
    private int bibNumber;
    private LocalDateTime timeStamp;
    private LocalDateTime officialStopTime;
    private boolean didNotStart = false;
    private boolean isFinished = false;
    private boolean quitRace = false;
    private LocalDateTime officialStartTime;
    private String gender;
    private int age;
    private double locationOnCourse = 0;

    public Athlete(String firstName, String lastName, String gender, int age, int bibNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.bibNumber = bibNumber;
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

    public int getBibNumber() { return bibNumber; }

    public void setBibNumber(int bibNumber) { this.bibNumber = bibNumber; }

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

    public boolean isDidNotStart() {
        return didNotStart;
    }

    public void setDidNotStart(boolean didNotStart) {
        this.didNotStart = didNotStart;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
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

    @Override
    public void notifyObservers() {
        for (IAthleteObserver observer : observerList) {
            observer.update(this);
        }
    }

    @Override
    public void subscribeObserver(IAthleteObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void unsubscribeObserver(IAthleteObserver observer) {
        observerList.remove(observer);
        observer.update(this);
    }

    public boolean isObservedBy(IAthleteObserver observer) { return observerList.contains(observer); }
}
