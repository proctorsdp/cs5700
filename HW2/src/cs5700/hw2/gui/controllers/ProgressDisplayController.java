package cs5700.hw2.gui.controllers;

import cs5700.hw2.application.observers.IAthleteObserver;
import cs5700.hw2.application.observers.ProgressTracker;
import cs5700.hw2.application.subjects.Athlete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.util.Timer;
import java.util.TimerTask;

public class ProgressDisplayController implements IDisplayController {
    private ProgressTracker observer = new ProgressTracker();
    private ObservableList<TableAthlete> athletes = FXCollections.observableArrayList();

    @FXML
    private TitledPane progressObserverTitledPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<TableAthlete> progessTableView;

    @FXML
    private TableColumn<TableAthlete, Integer> athleteColumn;

    @FXML
    private TableColumn<TableAthlete, ProgressBar> progressColumn;


    @Override
    public IAthleteObserver getObserver() {
        return observer;
    }

    @Override
    public void execute() {
        initTable();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateProgress();
            }
        }, 0, 300);
    }

    private void updateProgress() {
        athletes.clear();
        int i = 0;
        for (Athlete a : observer) {
            athletes.add(new TableAthlete(a));
            athletes.get(i++).getProgressBar().prefWidthProperty().bind(progressColumn.widthProperty());
        }
    }

    private void initTable() {
        athleteColumn.setCellValueFactory(new PropertyValueFactory<>("bibNum"));
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("progressBar"));

        progessTableView.setItems(athletes);
    }
}
