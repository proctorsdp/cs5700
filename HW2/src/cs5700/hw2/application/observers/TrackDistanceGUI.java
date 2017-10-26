package cs5700.hw2.application.observers;

import cs5700.hw2.application.subjects.Athlete;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class TrackDistanceGUI implements IAthleteObserver {
    private ArrayList<Athlete> athleteList = new ArrayList<>();
    private boolean guiDirty;
    XYChart.Series dataSeries1 = new XYChart.Series();
    private VBox vbox;
    private BarChart barChart;


    public void run(Stage primaryStage) {
        primaryStage.setTitle("Distance Tracker");
        initBarGraph();

        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void initBarGraph() {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Athletes");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Distance");

        dataSeries1.setName("Athletes");
        barChart = new BarChart(xAxis, yAxis);

        setDataSeries();

    }

    private void setDataSeries() {
        if (!barChart.getData().isEmpty()) {
            barChart.getData().removeAll();
        }

        for (Athlete a : athleteList) {
            dataSeries1.getData().add(new XYChart.Data(a.getBibNumber(), a.getLocationOnCourse()));
        }

        barChart.getData().add(dataSeries1);
    }

    @Override
    public void update(Athlete athlete) {
        if (!athleteList.contains(athlete)) {
            athleteList.add(athlete);
        }

        setDataSeries();
    }


}
