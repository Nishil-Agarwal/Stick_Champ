package com.example.ap_proj;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
//    @FXML
//    private Label totalscore1;
//
//    @FXML
//    private Label totalscore2;
//
//    @FXML
//    private Label cherries1;
//
//    @FXML
//    private Label cherries2;
//
//    @FXML
//    private Label incrementscore;

    private Stage stage;
    private Scene scene;
    private Pane root;

    private Player player;
    private Cherry cherry;
    private Mountain basemount;
    private Mountain targetmount;
    private Boolean key_pressed=false;
    private Stick stick;

    private Label end_scene_cherries;
    private Label end_scene_score;
    private Label totalsc1;
    private Label cherries1;

    private final Streams stream=new Streams();

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
        scene = new Scene(root,600,600);
        stage.setScene(scene);
        stage.show();
    }

    private int getCherries() throws IOException {
        return stream.readCherry();
    }

    private int getScore() throws IOException {
        return stream.readScore();
    }

    @FXML
    private void switchToEndScene(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EndScene.fxml"));

        this.end_scene_cherries=new Label(String.valueOf(getCherries()));
        this.end_scene_score=new Label(String.valueOf(getScore()));

        end_scene_cherries.setFont(Font.font("Arial Black",24));
        end_scene_cherries.setTextFill(Color.WHITE);
        end_scene_score.setLayoutX(329);
        end_scene_score.setLayoutY(120);

        end_scene_score.setFont(Font.font("Arial Black",24));
        end_scene_score.setTextFill(Color.WHITE);
        end_scene_cherries.setLayoutX(345);
        end_scene_cherries.setLayoutY(70);


        root.getChildren().add(end_scene_score);
        root.getChildren().add(end_scene_cherries);

        scene = new Scene(root,600,600);
        stage.setScene(scene);
        stage.show();
    }

    public void gameover(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EndScene.fxml"));

        Button resumebutton=(Button)root.lookup("#resume_button");
        resumebutton.setText("Revive?");

        this.end_scene_cherries=new Label(String.valueOf(getCherries()));
        this.end_scene_score=new Label(String.valueOf(getScore()));

        end_scene_cherries.setFont(Font.font("Arial Black",24));
        end_scene_cherries.setTextFill(Color.WHITE);
        end_scene_cherries.setLayoutX(329);
        end_scene_cherries.setLayoutY(120);

        end_scene_score.setFont(Font.font("Arial Black",24));
        end_scene_score.setTextFill(Color.WHITE);
        end_scene_score.setLayoutX(345);
        end_scene_score.setLayoutY(70);


        root.getChildren().add(end_scene_score);
        root.getChildren().add(end_scene_cherries);

        scene = new Scene(root,600,600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToGameScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        add_objects_to_pane(root);
        player.setlabels(root);
        System.out.println(player.getDeadStatus());
        if (player.getDeadStatus()==1){
            if (player.getcherries()>=5){
                player.reviving_ritual();
            }else{
                Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cherries Warning");
                alert.setHeaderText("You have insufficient cherries");
                alert.setContentText("Do you want to continue the Game");
                if(alert.showAndWait().get()== ButtonType.OK){}
            }
        }else{
            scene = new Scene(root,600,600);

            AnimationTimer gameover= new AnimationTimer() {
                @Override
                public void handle(long l) {
                    if (player.getDeath()==1){
                        try {
                            player.setDeath();
                            gameover(stage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };

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
    //            if (stick!=null) {
    //                ((Pane)((Scene) press.getSource()).getRoot()).getChildren().remove(stick.getstick());
    //            }
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
                gameover.start();
            });

            scene.setOnMouseReleased(press->{
                //Stopping the stick growth + making it fall down + making character move to end of stick
                AnimationTimer newmountappear=new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        if (player.completedCheck()==1){
                            player.setCompleted(0);
                            targetmount = new Mountain(-1,-1);
                            root.getChildren().add(targetmount.getmountain());
                            root.getChildren().add(targetmount.gettarget().getrect());
                            targetmount.movein();

                            root.getChildren().remove(cherry.getCherryImage());
                            try {
                                cherry=new Cherry();
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            ImageView result = cherry.try_generation(0,targetmount.getMountainStart());
                            if (result!=null){
                                root.getChildren().add(result);
                            }
                        }
                    }
                };
                newmountappear.start();
                key_pressed=false;
                stick_generating_animation.stop();
                stick.rotate(player,targetmount.getMountainStart(),targetmount.getMountainWidth(), targetmount.gettarget().start_xcoord(),this.cherry,this.stick,this.targetmount);
            });

            scene.setOnKeyReleased(key->{
                if(key.getCode()== KeyCode.S){          //Use S key to flip character
                    player.flip();
                }
            });

            stage.setScene(scene);
            stage.show();
        }
    }

    private void add_objects_to_pane(Pane root) throws IOException {
        this.player = new Player();
        this.basemount = new Mountain(0,70);
        this.targetmount = new Mountain(-1,-1);     //If -1 passed randomise
        cherry=new Cherry();
        ImageView result = cherry.try_generation(0,targetmount.getMountainStart());


        player.getplayer().setId("player");
        root.getChildren().add(player.getplayer());
        root.getChildren().add(basemount.getmountain());
        root.getChildren().add(targetmount.getmountain());
        root.getChildren().add(targetmount.gettarget().getrect());
        if (result!=null){
            root.getChildren().add(result);
        }

        basemount.movein();
        targetmount.movein();
    }
}



//    public void generatecherry(){}
//    public void generatemountains(){}
//    public void revive(){}