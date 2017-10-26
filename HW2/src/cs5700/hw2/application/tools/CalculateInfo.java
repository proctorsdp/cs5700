package cs5700.hw2.application.tools;

import cs5700.hw2.application.subjects.Athlete;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalculateInfo {
    private static CalculateInfo instance = null;
    private final double METERS_PER_MILE = 1609.34;
    private double raceMiles;
    private double raceMeters;

    private CalculateInfo() { }

    public static CalculateInfo getInstance() {
        if (instance == null) {
            instance = new CalculateInfo();
        }
        return instance;
    }

    public void setRaceMiles(double raceMiles) {
        this.raceMiles = raceMiles;
        this.raceMeters = milesToMeters(raceMiles);
    }

    public double getRaceMiles() {
        return raceMiles;
    }

    public double getRaceMeters() {
        return raceMeters;
    }

    public double milesToMeters(double miles) {
        return miles * METERS_PER_MILE;
    }

    public double metersToMiles(double meters) {
        return meters / METERS_PER_MILE;
    }

    public double getVelocity(Athlete a) {
        double dist = a.getLocationOnCourse();
        long[] time = getElapsedTime(a);
        double sec = getTotalSeconds(time);
        return sec == 0 ? 0 : dist / sec;
    }

    public int getTotalSeconds(long[] time) {
        return (int)(time[0] * 3600 + time[1] * 60 + time[2]);
    }

    public long[] getElapsedTime(Athlete a) {
        long[] elapsedTime = {0, 0, 0};
        LocalDateTime start = a.getOfficialStartTime();
        LocalDateTime current;

        if (start == null) {
            return elapsedTime;
        }

        if (!a.isFinished()) {
            current = a.getTimeStamp();
        } else {
            current = a.getOfficialStopTime();
        }

        Duration duration = Duration.between(start, current);
        elapsedTime[0] = duration.getSeconds() / 3600;
        elapsedTime[1] = (duration.getSeconds() % 3600) / 60;
        elapsedTime[2] = duration.getSeconds() % 60;

        return elapsedTime;
    }

    public long[] getPace(Athlete a) {
        long[] pace = {99, 99, 99};
        double v = getVelocity(a);

        if (v == 0 || a.isQuitRace() || a.isDidNotStart()) {
            return pace;
        }

        double dist = a.getLocationOnCourse() > (raceMeters) ? a.getLocationOnCourse() : (raceMeters);
        long seconds = (long)(dist / v);

        pace[0] = seconds / 3600;
        pace[1] = (seconds % 3600) / 60;
        pace[2] = seconds % 60;

        return pace;
    }

    public String locationToString(Athlete a) {
        double location = metersToMiles(a.getLocationOnCourse());
        if (location >= raceMiles - .05) {
            return "FINISHED";
        } else {
            return String.format("%3.2f", location);
        }
    }

    public String paceToString(Athlete a) {
        long[] pace = getPace(a);
        return String.format("%02d:%02d:%02d", pace[0], pace[1], pace[2]);
    }
}
