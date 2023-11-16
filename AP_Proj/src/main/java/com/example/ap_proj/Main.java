package com.example.ap_proj;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Main extends Application {
    private Player player;
    private Mountain mount0;
    private Mountain mount1;
    private Mountain mount2;
    private Mountain mount3;
    private ImageView backg_img;

    @Override
    public void start(Stage primarystage) throws IOException, InterruptedException {
        player=new Player();
        mount0=new Mountain(0);
        mount1=new Mountain(200);
        mount2=new Mountain(450);
        mount3=new Mountain(600);
        //Player player2=new Player();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Pane root = loader.load();

        backg_img = new ImageView(new Image("file:C:\\Users\\nishi\\Vs_Code_Projects\\IIITD\\SEM_3\\AP\\Assignments\\AP_Proj\\src\\main\\java\\com\\example\\ap_proj\\background.jpg"));

        root.getChildren().add(backg_img);
        root.getChildren().add(player.getplyr());
        root.getChildren().add(mount0.getmount());
        root.getChildren().add(mount1.getmount());
        root.getChildren().add(mount2.getmount());
        root.getChildren().add(mount3.getmount());

        Scene scene1 = new Scene(root, 884, 500);


//        Group newg1 = new Group();
//        newg1.getChildren().add(player.getplyr());
//
//        Scene scene1 = new Scene(newg1, 600, 600);

        scene1.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case S:
                    player.flip();
                    break;
                case J:
                    player.move();
                    break;
            }
        });

        primarystage.setTitle("Stick Hero");
        primarystage.setScene(scene1);
        primarystage.show();
    }

    class Player{
        private int height = 60;
        private int width = 50;
        private ImageView plyr;
        private int flipstatus=0;
        Player() throws FileNotFoundException {
            //plyr= new Rectangle(20,200, 30,40);
            Image character=new Image(new FileInputStream("C:\\Users\\nishi\\Vs_Code_Projects\\IIITD\\SEM_3\\AP\\Assignments\\AP_Proj\\src\\main\\java\\com\\example\\ap_proj\\Stickman.jpg"));
            plyr = new ImageView(character);
            plyr.setFitHeight(this.height);
            plyr.setFitWidth(this.width);
            plyr.setX(20);
            plyr.setY(200);
        }
        public ImageView getplyr(){
            return this.plyr;
        }
        public void move(){
            TranslateTransition movepath = new TranslateTransition(Duration.millis(2500), this.plyr);
            movepath.setToX(500);
            movepath.play();
        }

        public void flip() {
            RotateTransition flip=new RotateTransition(Duration.millis(200),this.plyr);
            flip.setByAngle(180);
            if (flipstatus==0){
                flipstatus=1;
                TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.plyr);
                flipping.setToY(this.height);
                flip.play();
                flipping.play();
            }else if (flipstatus==1){
                flipstatus=0;
                TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.plyr);
                flipping.setToY(0);
                flip.play();
                flipping.play();
            }
            //return;
        }
    }

    public class Mountain {
        private int baseHeight = 240;
        private int baseWidth = 80;
        private Rectangle mountain;

        public Mountain(int xCoord) {
            mountain = new Rectangle(xCoord, 260, baseWidth, baseHeight);

            try {
                //Image image = new Image("file:C:\\Users\\nishi\\Vs_Code_Projects\\IIITD\\SEM_3\\AP\\Assignments\\AP_Proj\\src\\main\\java\\com\\example\\ap_proj\\mount.jpg");
                this.mountain.setFill(Color.SADDLEBROWN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Rectangle getmount() {
            return this.mountain;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}