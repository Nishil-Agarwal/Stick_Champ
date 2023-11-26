package com.example.ap_proj;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Pane root;

    private Player player;
    private Mountain basemount;
    private Mountain targetmount;
    private Boolean key_pressed=false;
    private Stick stick;

    @FXML
    private void exit(ActionEvent event){
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit the Game");
        alert.setContentText("Do you want to exit the Game");
        if(alert.showAndWait().get()== ButtonType.OK){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        System.out.println("Exited the game Successfully.");
        stage.close();
        }
    }

    @FXML
    private void switchToStartScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToEndScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EndScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToGameScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        add_objects_to_pane(root);
        scene = new Scene(root,600,325);

        //For growing stick when mouse held :
        AnimationTimer stick_generating_animation = new AnimationTimer() {
            @Override
            public void handle(long num) {
                if (key_pressed){
                    try {
                        stick.increment_size();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        scene.setOnMousePressed(press->{
            //Creating stick object :
            ImageView plyr = player.getplayer();
            double xcoord = plyr.localToScene(plyr.getLayoutX(), plyr.getLayoutY()).getX();
            double ycoord = plyr.localToScene(plyr.getLayoutX(), plyr.getLayoutY()).getY();
            try{
                this.stick = new Stick(xcoord,ycoord);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
            root.getChildren().add(stick.getstick());

            //Stick size growing starts :
            key_pressed=true;
            stick_generating_animation.start();
        });

        scene.setOnMouseReleased(press->{
            //Stopping the stick growth + making it fall down + making character move to end of stick
            key_pressed=false;
            stick_generating_animation.stop();
            stick.rotate(player);
        });

        scene.setOnKeyReleased(key->{
            if(key.getCode()== KeyCode.S){          //Use S key to flip character
                player.flip();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    private void add_objects_to_pane(Pane root) throws FileNotFoundException {
        this.player = new Player();
        this.basemount = new Mountain(0,90);
        this.targetmount = new Mountain(-1,-1);     //If -1 passed randomise

        root.getChildren().add(player.getplayer());
        root.getChildren().add(basemount.getmountain());
        root.getChildren().add(targetmount.getmountain());
    }
}



//    public void generatecherry(){}
//    public void generatemountains(){}
//    public void revive(){}
