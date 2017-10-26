package cs5700.hw2.application.observers;

import cs5700.hw2.application.subjects.Athlete;
import java.util.Dictionary;
import java.util.Hashtable;

public interface IAthleteObserver {
    Dictionary<Integer, Athlete> athletesObserved = new Hashtable<>();
    Dictionary<Integer, Boolean> sendUpdate = new Hashtable<>();

    void update(Athlete athlete);
}
