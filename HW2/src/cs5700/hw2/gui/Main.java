package cs5700.hw2.gui;

import Exceptions.ApplicationException;
import cs5700.hw2.application.managers.RaceController;
import cs5700.hw2.application.tools.CalculateInfo;
import cs5700.hw2.application.tools.ManageList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static RaceController raceController;
    private static CalculateInfo calculateInfo;
    private static ManageList manageList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/cs5700/hw2/gui/fxml/raceMonitorDisplay.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void initRaceController(String fileName, int raceMiles) throws InterruptedException, ApplicationException {
        calculateInfo = CalculateInfo.getInstance();
        calculateInfo.setRaceMiles(raceMiles);

        manageList = ManageList.getInstance();

        raceController = RaceController.getInstance(fileName, raceMiles);
        raceController.run();
    }


}
