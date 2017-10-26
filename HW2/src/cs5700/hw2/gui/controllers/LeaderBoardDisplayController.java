package cs5700.hw2.gui.controllers;

import cs5700.hw2.application.observers.IAthleteObserver;
import cs5700.hw2.application.observers.TopTenAthletes;
import cs5700.hw2.application.subjects.Athlete;
import cs5700.hw2.application.tools.ManageList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LeaderBoardDisplayController implements IDisplayController {
    private TopTenAthletes observer;
    private ObservableList<TableAthlete> leaderBoard;

    @FXML
    public TableView leaderBoardTableView;

    @FXML
    public TableColumn idColumn;

    @FXML
    public TableColumn lastNameCol;

    @FXML
    public TableColumn firstNameCol;

    @FXML
    public TableColumn distanceColumn;

    @FXML
    public TableColumn paceColumn;

    @FXML
    private TitledPane leaderBoardTitledPane;

    @FXML
    private AnchorPane anchorPane;


    private void updateLeaderBoard() {
        leaderBoard.clear();
        for (Athlete a : observer) {
            leaderBoard.add(new TableAthlete(a));
        }
    }

    @Override
    public IAthleteObserver getObserver() {
        return observer;
    }

    @Override
    public void execute() {
        this.observer = new TopTenAthletes();
        leaderBoard = FXCollections.observableArrayList();
        initTable();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateLeaderBoard();
            }
        }, 0, 2000);
    }

    private void initTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bibNum"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        paceColumn.setCellValueFactory(new PropertyValueFactory<>("pace"));

        leaderBoardTableView.setItems(leaderBoard);
    }
}
