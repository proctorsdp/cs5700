package cs5700.hw2;

import Exceptions.ApplicationException;
import Racedata.SimulatedDataSource;

public class RaceController {
    private final static int MINUTES = 3;
    private final static int MS_TO_MIN = 60000;

    public void run() throws ApplicationException, InterruptedException {
        SimulatedDataSource simulatedDataSource = new SimulatedDataSource();
        simulatedDataSource.setInputFilename("data/Short Race Simulation-01.csv");

        RaceHandler raceHandler = new RaceHandler();

        simulatedDataSource.setHandler(raceHandler);

        simulatedDataSource.Start();
        Thread.sleep(MS_TO_MIN * MINUTES);
        simulatedDataSource.Stop();
    }
}
