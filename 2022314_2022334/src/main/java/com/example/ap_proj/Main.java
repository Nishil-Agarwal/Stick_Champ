package com.example.ap_proj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private Pane home;
    private Scene home_screen;
    @Override
    public void start(Stage primarystage) throws IOException{
        FXMLLoader template = new FXMLLoader(getClass().getResource("StartScene.fxml"));
        home = template.load();
        home_screen = new Scene(home,600,325);
        primarystage.setTitle("Stick Hero");
        primarystage.setScene(home_screen);
        primarystage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}