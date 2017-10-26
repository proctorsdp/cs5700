package cs5700.hw2.application.observers;

import cs5700.hw2.application.tools.ManageList;
import cs5700.hw2.application.subjects.Athlete;
import java.util.ArrayList;

public class CompareAthletes extends ArrayList<Athlete> implements IAthleteObserver {
    private ManageList manage;

    public CompareAthletes() {
        this.manage = ManageList.getInstance();
    }

    @Override
    public void update(Athlete athlete) {

        int key = athlete.getBibNumber();
        if (athletesObserved.get(key) == null) {
            athletesObserved.put(key, athlete);
        }

        if (!this.contains(athlete)) {
            this.add(athlete);
        }

        if (this.size() > 1) {
            manage.sortByPace(this);
        }

        System.out.println(manage.listToString(this));
    }




}
