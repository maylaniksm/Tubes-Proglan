package com.example.tubes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SewaLapanganFutsalApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sewa Lapangan Futsal");

        // Create the main UI and set the stage
        MainUI mainUI = new MainUI();
        Scene scene = new Scene(mainUI.getGrid(), 450, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
