package cs5700.hw2;

import Exceptions.ApplicationException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ApplicationException {
        RaceController raceController = new RaceController();
        raceController.run();
    }
}
