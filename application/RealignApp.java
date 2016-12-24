package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * So here's the deal: This app was written in a quick-and-dirty fashion.  There's a few things
 * that don't hew to OOP principles -- partially because of limits in JavaFX I can't get around (for example,
 * if you need a table of 20 things, there doesn't seem to be a way to automate assigning each table item
 * to a variable in the presenter), and partially because this is a simple program that probably won't be extended much,
 * and sometimes quick but hard-to-change is better than slow but extensible (for example, PossibilityManager has four variables
 * where it should have an array).
 * 
 * A note on the architechture:
 * 	-The application.view package contains the the FXML file defining the GUI, and a controller/model class that takes user input,
 * 		and does basic calculations on it before showing the output.
 *  -The appliction.logic package contains classes for simulating realignments.  It does not interact with the outside world.
 *  - The utils package contains utility classes that I use in other projects of mine.
 *
 */

public class RealignApp extends Application {

    private Stage primaryStage;

    public RealignApp() {

    }


    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Twilight Struggle Religner");
        this.primaryStage.setResizable(false);


        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RealignApp.class.getResource("view/DataWindow.fxml"));
            AnchorPane dataWindow = (AnchorPane) loader.load();
            dataWindow.setId("root");
            Scene scene = new Scene(dataWindow);
            scene.getStylesheets().add("/application/view/style.css");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();

    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}