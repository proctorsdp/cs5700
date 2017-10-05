package cs5700.hw2;

import java.util.ArrayList;

public class AthleteList extends ArrayList<Runner> implements IAthleteObservable {
    ArrayList<IAthleteObserverList> observersList = new ArrayList<>();
    public void notifyObservers() {

    }

    public void subscribeObserver(IAthleteObserverList observer) {

    }

    public void unsubscribeObserver(IAthleteObserverList observer) {

    }
}
