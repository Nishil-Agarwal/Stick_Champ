package com.example.ap_proj;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;
import javafx.util.Duration;


import java.io.IOException;

public class HelloApplication extends Application {
    private Player player;

    @Override
    public void start(Stage primarystage) throws IOException {
        player=new Player();

        Group newg = new Group();
        newg.getChildren().add(player.getplyr());

        Scene scene1 = new Scene(newg,600,600);
        scene1.setOnKeyPressed(e->{
            switch (e.getCode()){
                case S:
                    player.flip();
                    break;
                case SPACE:
                    player.move();
                    break;
            }
        });
        primarystage.setTitle("Stick Hero");
        primarystage.setScene(scene1);
        primarystage.show();
    }

    class Player{
        private int height = 20;
        private int width = 20;
        private String colour = "Black";
        private Rectangle plyr;
        private int flipstatus=0;
        Player(){
            plyr= new Rectangle(20,200, 30,40);
        }
        public Rectangle getplyr(){
            return this.plyr;
        }
        public void move(){
            TranslateTransition movepath = new TranslateTransition(Duration.millis(1500), this.plyr);
            movepath.setToX(300);
            movepath.play();
        }

        public void flip() {
            if (flipstatus==0){
                flipstatus=1;
                TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.plyr);
                flipping.setToY(40);
                flipping.play();
            }else if (flipstatus==1){
                flipstatus=0;
                TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.plyr);
                flipping.setToY(0);
                flipping.play();
            }
            //return;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}