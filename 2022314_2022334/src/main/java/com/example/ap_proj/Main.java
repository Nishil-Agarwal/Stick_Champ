package com.example.ap_proj;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private Pane home;
    private Scene home_screen;
    private MusicController musicControl;
    @Override
    public void start(Stage primarystage) throws IOException{
        musicControl=new MusicController();
        musicControl.music();
        Result result = JUnitCore.runClasses(StickTest.class);
        for(Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        FXMLLoader template = new FXMLLoader(getClass().getResource("StartScene.fxml"));
        home = template.load();
        home_screen = new Scene(home,600,600);
        primarystage.setTitle("Stick Hero");
        primarystage.setScene(home_screen);
        primarystage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}