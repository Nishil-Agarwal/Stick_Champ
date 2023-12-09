package com.example.ap_proj;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static java.lang.Thread.sleep;

public class Player{
    public void setMove_complete(int move_complete) {
        this.move_complete = move_complete;
    }

    public int getMove_complete() {
        return move_complete;
    }

    private int move_complete=0;
    private final int height = 60;
    private final int width = 50;
    private ImageView sprite_image;
    private boolean flipped=false;        //false = upright

    private Label incremented;
    private Label totalsc1;
    private int score;
    private int cherries;

    Player() throws FileNotFoundException {
        Image character=new Image(new FileInputStream("/Users/namitgupta/Downloads/AP_Project-main/2022314_2022334/src/main/resources/com/example/ap_proj/Stickman.png"));
        sprite_image = new ImageView(character);
        sprite_image.setFitHeight(this.height);
        sprite_image.setFitWidth(this.width);
        sprite_image.setX(20);
        sprite_image.setY(405);
    }

    public ImageView getplayer(){
        return this.sprite_image;
    }

    public void move(double distance,int target_mountain_start, int target_mountain_finish,double red_target_start){
        TranslateTransition movepath = new TranslateTransition(Duration.millis(distance*9), this.sprite_image);
        movepath.setByX(distance+40);
        movepath.setOnFinished(checkfall->{
            double top_left_xcoord=sprite_image.localToScene(sprite_image.getLayoutX(), sprite_image.getLayoutY()).getX();
            double xcoord=top_left_xcoord+10+sprite_image.getX();
//            System.out.println(distance);
//            System.out.println(top_left_xcoord);
//            System.out.println(xcoord);
//            System.out.println(target_mountain_start);
            if (xcoord>target_mountain_finish || xcoord<target_mountain_start){
                falldown();
            }else{
                if (xcoord>red_target_start && xcoord<(red_target_start+12)){
                    try {
                        moveback(90);
                        move_complete=1;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    try {
                        moveback(40);
                        move_complete=1;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        movepath.play();
    }

    public void flip() {
        RotateTransition flip=new RotateTransition(Duration.millis(200),this.sprite_image);
        flip.setByAngle(180);

        if (!flipped){
            ScaleTransition horizontalflip = new ScaleTransition(Duration.millis(200),this.sprite_image);
            horizontalflip.setFromX(1);
            horizontalflip.setToX(-1);
            flipped=true;
            TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.sprite_image);
            flipping.setByY(this.height);
            flip.play();
            horizontalflip.play();
            flipping.play();
        }else{
            ScaleTransition horizontalflip = new ScaleTransition(Duration.millis(200),this.sprite_image);
            horizontalflip.setFromX(-1);
            horizontalflip.setToX(1);
            flipped=false;
            TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.sprite_image);
            flipping.setByY(-this.height);
            flip.play();
            horizontalflip.play();
            flipping.play();
        }
    }

    public void falldown(){
        TranslateTransition edge = new TranslateTransition(Duration.millis(100), this.sprite_image);
        edge.setByX(10);
        edge.play();
        edge.setOnFinished(e->{
            RotateTransition fall=new RotateTransition(Duration.millis(700),this.sprite_image);
            fall.setByAngle(90);
            if (!flipped){
                TranslateTransition falling = new TranslateTransition(Duration.millis(2000), this.sprite_image);
                falling.setByY(200);
                fall.play();
                falling.play();
            }else{
                TranslateTransition flipping = new TranslateTransition(Duration.millis(2000), this.sprite_image);
                flipping.setByY(160);
                flipping.play();
            }
        });
    }

    public void moveback(int incrementscore) throws InterruptedException {
        this.score+=incrementscore;
        TranslateTransition back = new TranslateTransition(Duration.millis(1000), this.sprite_image);
        back.setToX(0);
        back.play();
        setscore(incrementscore);
    }

    public void gameover(){

    }

    public void reviving_ritual(){}

    public int getcherries(){
        return this.cherries;
    }

    public int getscore(){
        return this.score;
    }

    public void setscore(int latestscore) throws InterruptedException {
        Pane root=((Pane)(this.sprite_image.getScene().getRoot()));
        root.getChildren().remove(totalsc1);

        this.incremented=new Label(("+"+String.valueOf(latestscore)));
        this.totalsc1=new Label(String.valueOf(this.score));

        incremented.setTextFill(Color.RED);
        if (latestscore==90){
            incremented.setFont(Font.font("Arial Black",50));
            incremented.setLayoutX(245);
        }else {
            incremented.setFont(Font.font("Arial Black",22));
            incremented.setLayoutX(273);
        }
        incremented.setLayoutY(144);
        totalsc1.setFont(Font.font("Arial Black",16));
        totalsc1.setTextFill(Color.WHITE);
        totalsc1.setLayoutX(537);
        totalsc1.setLayoutY(40);

        root.getChildren().add(incremented);
        root.getChildren().add(totalsc1);
        FadeTransition fade=new FadeTransition(Duration.millis((700)),totalsc1);
        fade.setOnFinished(e->{
            root.getChildren().remove(incremented);
        });
        fade.play();
    }
}
