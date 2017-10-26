package cs5700.hw2.application.observers;

import cs5700.hw2.application.subjects.Athlete;
import java.util.ArrayList;

public class ProgressTracker extends ArrayList<Athlete> implements IAthleteObserver {
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
    }


}
