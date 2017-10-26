package cs5700.hw2.application.managers;

import Messages.*;
import Racedata.IAthleteUpdateHandler;
import cs5700.hw2.application.subjects.Athlete;
import cs5700.hw2.application.subjects.AthleteList;


public class UpdateHandler implements IAthleteUpdateHandler {
    private static UpdateHandler instance = null;
    private AthleteList athleteList = AthleteList.getInstance();
    private Athlete athlete;

    private UpdateHandler() {}

    public static UpdateHandler getInstance() {
        if (instance == null) {
            instance = new UpdateHandler();
        }
        return instance;
    }

    @Override
    public void ProcessUpdate(AthleteUpdate athleteUpdate) {
        athlete = athleteList.getAthlete(athleteUpdate.getBibNumber());
        switch (athleteUpdate.getUpdateType()) {
            case Registered:
                registerAthlete((RegistrationUpdate)athleteUpdate);
                break;
            case DidNotStart:
                athleteDidNotStart((DidNotStartUpdate)athleteUpdate);
                break;
            case Started:
                athleteStarted((StartedUpdate)athleteUpdate);
                break;
            case OnCourse:
                locationOnCourse((LocationUpdate)athleteUpdate);
                break;
            case DidNotFinish:
                athleteDidNotFinish((DidNotFinishUpdate)athleteUpdate);
                break;
            case Finished:
                athleteFinished((FinishedUpdate)athleteUpdate);
                break;
        }
        System.out.println(athleteUpdate.toString());
        notifyObservers(athlete);
    }

    private void registerAthlete(RegistrationUpdate athleteUpdate) {
        athleteList.add(new Athlete(
                athleteUpdate.getFirstName(),
                athleteUpdate.getLastName(),
                athleteUpdate.getGender(),
                athleteUpdate.getAge(),
                athleteUpdate.getBibNumber()
        ));
    }

    private void locationOnCourse(LocationUpdate athleteUpdate) {
        athlete.setLocationOnCourse(athleteUpdate.getLocationOnCourse());
        athlete.setTimeStamp(athleteUpdate.getTimestamp());
    }

    private void athleteDidNotStart(DidNotStartUpdate athleteUpdate) {
        athlete.setDidNotStart(true);
    }

    private void athleteStarted(StartedUpdate athleteUpdate) {
        athlete.setOfficialStartTime(athleteUpdate.getOfficialStartTime());
        athlete.setTimeStamp(athleteUpdate.getOfficialStartTime());
    }

    private void athleteDidNotFinish(DidNotFinishUpdate athleteUpdate) {
        athlete.setQuitRace(true);
        athlete.setTimeStamp(athleteUpdate.getTimestamp());
    }

    private void athleteFinished(FinishedUpdate athleteUpdate) {
        athlete.setFinished(true);
        athlete.setOfficialStopTime(athleteUpdate.getOfficialEndTime());
    }

    private void notifyObservers(Athlete athlete) {
        athleteList.notifyObservers(athlete);
    }
}
