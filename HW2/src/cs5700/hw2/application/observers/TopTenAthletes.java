package cs5700.hw2.application.observers;

import cs5700.hw2.application.tools.ManageList;
import cs5700.hw2.application.subjects.Athlete;
import java.util.ArrayList;


public class TopTenAthletes extends ArrayList<Athlete> implements IAthleteObserver {
    private ManageList manage;
    private final int MAX_SIZE = 10;
    private boolean printToConsole = false;

    public TopTenAthletes() {
        this.manage = ManageList.getInstance();
    }

    @Override
    public void update(Athlete athlete) {

        int key = athlete.getBibNumber();
        if (athletesObserved.get(key) == null) {
            athletesObserved.put(key, athlete);
        }

        if (athlete.isQuitRace() || athlete.isDidNotStart() || !athlete.isObservedBy(this)) {
            if (this.contains(athlete)) {
                this.remove(athlete);
            }
        } else if (!this.contains(athlete)) {
            this.add(athlete);
        }

        manage.sortByPace(this);

        if (this.size() > MAX_SIZE) {
            this.subList(MAX_SIZE, this.size()).clear();
        }

        if (printToConsole && athlete.getBibNumber() % 11 == 0) {
            System.out.println(manage.listToString(this));
        }
    }
}
