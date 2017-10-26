package cs5700.hw2.application.managers;

import Exceptions.ApplicationException;
import Racedata.SimulatedDataSource;
import cs5700.hw2.application.tools.CalculateInfo;

public class RaceController implements Runnable {
    private Thread t;
    private static RaceController instance = null;
    private final static int MINUTES = 10;
    private final static int MS_TO_MIN = 60000;
    private double raceMiles;
    private String fileName;
    private String courseName;
    private String eventName;

    private RaceController(String fileName, double raceMiles) {
        this.fileName = fileName;
        this.raceMiles = raceMiles;
    }

    public static RaceController getInstance(String fileName, double raceMiles) {
        if (instance == null) {
            instance = new RaceController(fileName, raceMiles);
        }
        return instance;
    }

    public double getRaceMiles() {
        return raceMiles;
    }

    public void setRaceMiles(int raceMiles) {
        this.raceMiles = raceMiles;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void run() {
        SimulatedDataSource simulatedDataSource = new SimulatedDataSource();
        UpdateHandler updateHandler = UpdateHandler.getInstance();

        simulatedDataSource.setInputFilename(fileName);
        simulatedDataSource.setHandler(updateHandler);

        CalculateInfo calculateInfo = CalculateInfo.getInstance();
        calculateInfo.setRaceMiles(raceMiles);

        try {
            simulatedDataSource.Start();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        try {
            t.sleep(MS_TO_MIN * MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simulatedDataSource.Stop();
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}
