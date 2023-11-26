package com.example.ap_proj;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static java.lang.Thread.sleep;

public class Stick{
    private final int WIDTH =5;
    private Rectangle stick;
    private int height=0;

    private ImageView skin;

    public Stick(double playerxcoord, double playerycoord) throws InterruptedException {
        this.stick = new Rectangle(playerxcoord+70,playerycoord+205, WIDTH,0);
        this.stick.setFill(Color.DARKORANGE);
    }

    public Rectangle getstick(){
        return this.stick;
    }

    public void increment_size() throws InterruptedException {
        this.height+=10;
        stick.setHeight(stick.getHeight()+10);
        stick.setY(stick.getY()-10);           //Must move stick upwards too else size grows downwards
        sleep(150);
    }

    public void rotate(Player player){
        TranslateTransition trans = new TranslateTransition(Duration.millis(1000),this.stick);
        trans.setByY((double)this.height/2+3);
        trans.setByX((double)this.height/2+5);
        RotateTransition rot = new RotateTransition(Duration.millis(1000),this.stick);
        rot.setByAngle(90);
        rot.play();
        trans.play();
        trans.setOnFinished(event->{
            player.move(this.height);
        });
    }

    public double getlength(){
        return this.height;
    }

    public void setskin(){}
}
