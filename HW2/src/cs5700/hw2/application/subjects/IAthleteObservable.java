package cs5700.hw2.application.subjects;

import cs5700.hw2.application.observers.IAthleteObserver;
import java.util.ArrayList;

public interface IAthleteObservable {
    ArrayList<IAthleteObserver> observerList = new ArrayList<>();

    public void notifyObservers();

    public void subscribeObserver(IAthleteObserver observer);

    public void unsubscribeObserver(IAthleteObserver observer);
}
