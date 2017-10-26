package cs5700.hw2.gui.controllers;

import Exceptions.ApplicationException;
import cs5700.hw2.application.tools.ObserverTypes;
import cs5700.hw2.application.managers.RaceController;
import cs5700.hw2.application.subjects.Athlete;
import cs5700.hw2.application.subjects.AthleteList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RaceMonitorDisplayController  {
    private ObserverTypes newObserver = null;
    private AthleteList athletes = AthleteList.getInstance();
    private ArrayList<IDisplayController> observers = new ArrayList<>();
    private ObservableList<TableAthlete> subscribedAthletes = FXCollections.observableArrayList();
    private ObservableList<TableAthlete> unSubscribedAthletes = FXCollections.observableArrayList();
    private IDisplayController chosenObserver = null;

    @FXML
    private TitledPane raceMonitorTitledPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ListView<IDisplayController> observerListView;

    @FXML
    private Button createObserverButton;

    @FXML
    private Text observerListViewTitle;

    @FXML
    private Button createRaceButton;

    @FXML
    private Button subscribeButton;

    @FXML
    private Button unSubscribeButton;

    @FXML
    private Text subscribedListViewTitle;

    @FXML
    private Text athleteListViewTitle;

    @FXML
    private TableView<TableAthlete> subscribedTableView;

    @FXML
    private TableColumn<TableAthlete, Integer> subBibNum;

    @FXML
    private TableColumn<TableAthlete, StringProperty> subLastName;

    @FXML
    private TableView<TableAthlete> athleteTableView;

    @FXML
    private TableColumn<TableAthlete, IntegerProperty> bibNum;

    @FXML
    private TableColumn<TableAthlete, StringProperty> lastName;

    @FXML
    void createObserver(ActionEvent event) throws IOException {
        CreateObserverDisplayController newDisplay = (CreateObserverDisplayController)
                displayNewWindow("/cs5700/hw2/gui/fxml/createObserverDisplay.fxml", true);
        newObserver = newDisplay.getNewObserver();

        addObserver();
        refreshAthleteLists();
    }

    @FXML
    void createRace(ActionEvent event) throws IOException, InterruptedException, ApplicationException {
        RaceInfoDisplayController raceInfo = (RaceInfoDisplayController)
                displayNewWindow("/cs5700/hw2/gui/fxml/raceInfoDisplay.fxml",true);

        RaceController raceController = RaceController.getInstance(raceInfo.getFileName(), raceInfo.getRaceLength());
        raceController.setCourseName(raceInfo.getCourseName());
        raceController.setEventName(raceInfo.getEventName());

        Thread newThread = new Thread(raceController);
        newThread.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                initTable();
                createObserverButton.setDisable(false);
                loadAthletes();
            }
        }, 5000);
    }

    @FXML
    void subscribeTo(ActionEvent event) {
        if (chosenObserver != null) {
            ObservableList<TablePosition> selectedCells = athleteTableView.getSelectionModel().getSelectedCells();
            for (TablePosition t : selectedCells) {
                TableAthlete a = athleteTableView.getItems().get(t.getRow());
                athletes.subscribeObserver(chosenObserver.getObserver(), a.getBibNum());
            }
            refreshAthleteLists();
        }
    }

    @FXML
    void unSubscribeTo(ActionEvent event) {
        if (chosenObserver != null) {
            ObservableList<TablePosition> selectedCells = subscribedTableView.getSelectionModel().getSelectedCells();
            for (TablePosition t : selectedCells) {
                TableAthlete a = subscribedTableView.getItems().get(t.getRow());
                athletes.unsubscribeObserver(chosenObserver.getObserver(), a.getBibNum());
            }
            refreshAthleteLists();
        }
    }

    public void addObserver() throws IOException {
        String fxmlFile = "";
        boolean showAndWait = false;
        switch (newObserver) {
            case EMAIL:
                fxmlFile = "/cs5700/hw2/gui/fxml/emailDisplay.fxml";
                showAndWait = true;
                break;
            case COMPARE:
                fxmlFile = "";
                break;
            case LEADERBOARD:
                fxmlFile = "/cs5700/hw2/gui/fxml/leaderBoardDisplay.fxml";
                break;
            case PROGRESS:
                fxmlFile = "/cs5700/hw2/gui/fxml/progressDisplay.fxml";
                break;
        }

        observers.add((IDisplayController) displayNewWindow(fxmlFile,showAndWait));
        observers.get(observers.size()-1).execute();

        refreshObserverListView();
        refreshAthleteLists();
    }

    private void refreshObserverListView() {
        observerListView.getItems().clear();
        for (IDisplayController o : observers) {
            observerListView.getItems().add(o);
        }
    }

    private void loadAthletes() {
        for (Athlete a : athletes) {
            unSubscribedAthletes.add(new TableAthlete(a));
        }
    }

    private void refreshAthleteLists() {
        subscribedAthletes.clear();
        unSubscribedAthletes.clear();
        for (Athlete a : athletes) {
            if (chosenObserver != null && a.isObservedBy(chosenObserver.getObserver())) {
                subscribedAthletes.add(new TableAthlete(a));
            } else {
                unSubscribedAthletes.add(new TableAthlete(a));
            }
        }
        subscribedTableView.refresh();
        athleteTableView.refresh();
    }

    private void initTable() {
       subBibNum.setCellValueFactory(new PropertyValueFactory<>("bibNum"));
       subLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
       bibNum.setCellValueFactory(new PropertyValueFactory<>("bibNum"));
       lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

       subscribedTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       athleteTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

       subscribedTableView.setItems(subscribedAthletes);
       athleteTableView.setItems(unSubscribedAthletes);
    }

    public void selectObserver(MouseEvent mouseEvent) {
        if (observerListView.selectionModelProperty().get().getSelectedIndices().size() == 1) {
            chosenObserver = observerListView.selectionModelProperty().get().getSelectedItem();
            subscribeButton.setDisable(false);
            unSubscribeButton.setDisable(false);
        } else {
            chosenObserver = null;
            subscribeButton.setDisable(true);
            unSubscribeButton.setDisable(true);
        }
        refreshAthleteLists();
    }

    private Object displayNewWindow(String fxmlFile, boolean showAndWait) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        if (showAndWait) {
            stage.showAndWait();
        } else {
            stage.show();
        }

        return fxmlLoader.getController();
    }
}

