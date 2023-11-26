package com.example.ap_proj;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player{
    private final int height = 60;
    private final int width = 50;
    private ImageView sprite_image;
    private boolean flipped=false;        //false = upright

    private int score;
    private int cherries;
    private int coins;

    Player() throws FileNotFoundException {
        Image character=new Image(new FileInputStream("src\\main\\resources\\com\\example\\ap_proj\\Stickman.png"));
        sprite_image = new ImageView(character);
        sprite_image.setFitHeight(this.height);
        sprite_image.setFitWidth(this.width);
        sprite_image.setX(20);
        sprite_image.setY(150);
    }

    public ImageView getplayer(){
        return this.sprite_image;
    }

    public void move(double distance){
        TranslateTransition movepath = new TranslateTransition(Duration.millis(2500), this.sprite_image);
        movepath.setByX(distance);
        movepath.play();
    }

    public void flip() {
        RotateTransition flip=new RotateTransition(Duration.millis(200),this.sprite_image);
        flip.setByAngle(180);
        if (!flipped){
            flipped=true;
            TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.sprite_image);
            flipping.setByY(this.height);           //tested-was settoy earlier
            flip.play();
            flipping.play();
        }else{
            flipped=false;
            TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.sprite_image);
            flipping.setByY(-this.height);         //tested-was settoy(0); earlier
            flip.play();
            flipping.play();
        }
    }

    public void falldown(){
        RotateTransition fall=new RotateTransition(Duration.millis(700),this.sprite_image);
        fall.setByAngle(90);
        if (!flipped){
            TranslateTransition falling = new TranslateTransition(Duration.millis(2000), this.sprite_image);
            falling.setByY(100);
            fall.play();
            falling.play();
        }else{
            TranslateTransition flipping = new TranslateTransition(Duration.millis(2000), this.sprite_image);
            flipping.setByY(60);
            flipping.play();
        }
    }

    public void reviving_ritual(){}
    public void cherrygained(){}
    public void coinsgained(){}
    public void setscore(){}
}