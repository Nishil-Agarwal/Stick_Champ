package com.example.ap_proj;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private int a;

    public void meth() {
        System.out.println(a);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loadhome = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Pane roothome = loadhome.load();
        Scene scenehome = new Scene(roothome, 600, 325);


        primaryStage.setTitle("JavaFX Example");
        primaryStage.setScene(scenehome);
        primaryStage.show();

        a = 1; // Set the value of 'a' after the stage is shown
    }

    public static void main(String[] args) {
        launch(args);
    }
}



