package cs5700.hw2.application.subjects;

import cs5700.hw2.application.observers.IAthleteObserver;
import java.util.ArrayList;
import java.util.Optional;

public class AthleteList extends ArrayList<Athlete> {
    private static AthleteList instance = null;
    private double raceMiles;

    private AthleteList() {}

    public static AthleteList getInstance() {
        if (instance == null) {
            instance = new AthleteList();
        }
        return instance;
    }

    public void notifyObservers(Athlete athlete) {
        if (athlete != null) {
            this.get(this.indexOf(athlete)).notifyObservers();
        }
    }

    public Athlete getAthlete(int bibNumber) {
        Optional<Athlete> r = this.stream().filter(a -> a.getBibNumber() == bibNumber).findFirst();
        return r.orElse(null);
    }

    public void subscribeObserver(IAthleteObserver observer, int bibNumber) {
        getAthlete(bibNumber).subscribeObserver(observer);
    }

    public void unsubscribeObserver(IAthleteObserver observer, int bibNumber) {
        getAthlete(bibNumber).unsubscribeObserver(observer);
    }

    public double getRaceMiles() {
        return raceMiles;
    }

    public void setRaceMiles(double raceMiles) {
        this.raceMiles = raceMiles;
    }
}
