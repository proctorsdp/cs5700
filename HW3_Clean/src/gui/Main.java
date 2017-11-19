package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader menuBarLoader = new FXMLLoader(getClass().getResource("/gui/MenuBar.fxml"));
        FXMLLoader toolBarLoader = new FXMLLoader(getClass().getResource("/gui/ToolBar.fxml"));
        Controller controller = new Controller(menuBarLoader, toolBarLoader, new Pane());

        primaryStage.setTitle("UML Diagrams");
        primaryStage.setScene(new Scene(controller, 1200, 900));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
