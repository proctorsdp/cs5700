package cs5700.hw2;

import Messages.*;
import Racedata.IAthleteUpdateHandler;

import java.util.Optional;

public class RaceHandler implements IAthleteUpdateHandler {
    private AthleteList athleteList = new AthleteList();
    private Runner runner;

    @Override
    public void ProcessUpdate(AthleteUpdate athleteUpdate) {
        findRunner(athleteUpdate);
        switch (athleteUpdate.getUpdateType()) {
            case DidNotStart: athleteDidNotStart((DidNotStartUpdate)athleteUpdate);
                break;
            case Started: athleteStarted((StartedUpdate)athleteUpdate);
                break;
            case OnCourse: locationOnCourse((LocationUpdate)athleteUpdate);
                break;
            case DidNotFinish: athleteDidNotFinish((DidNotFinishUpdate)athleteUpdate);
                break;
            case Finished: athleteFinished((FinishedUpdate)athleteUpdate);
                break;
        }
        System.out.println(athleteUpdate.toString());
        notifyObservers();
    }

    private void registerAthlete(RegistrationUpdate athleteUpdate) {
        athleteList.add(new Runner(
                athleteUpdate.getFirstName(),
                athleteUpdate.getLastName(),
                athleteUpdate.getGender(),
                athleteUpdate.getAge(),
                athleteUpdate.getBibNumber()
        ));
    }

    private void locationOnCourse(LocationUpdate athleteUpdate) {
        runner.setLocationOnCourse(athleteUpdate.getLocationOnCourse());
        runner.setTimeStamp(athleteUpdate.getTimestamp());
    }

    private void athleteDidNotStart(DidNotStartUpdate athleteUpdate) {
        runner.setNotStarted(true);
    }

    private void athleteStarted(StartedUpdate athleteUpdate) {
        runner.setOfficialStartTime(athleteUpdate.getOfficialStartTime());
        runner.setTimeStamp(athleteUpdate.getOfficialStartTime());
    }

    private void athleteDidNotFinish(DidNotFinishUpdate athleteUpdate) {
        runner.setQuitRace(true);
        runner.setTimeStamp(athleteUpdate.getTimestamp());
    }

    private void athleteFinished(FinishedUpdate athleteUpdate) {
        runner.setNotFinished(true);
        runner.setTimeStamp(athleteUpdate.getOfficialEndTime());
    }

    private void notifyObservers() {
        athleteList.notifyObservers();
    }

    private void findRunner(AthleteUpdate athleteUpdate) {
        Optional<Runner> r = athleteList.stream().filter(a -> a.getBibNumber() == athleteUpdate.getBibNumber()).findFirst();
        if (r.isPresent()) {
            runner = r.get();
        } else {
            registerAthlete((RegistrationUpdate)athleteUpdate);
        }
    }
}
