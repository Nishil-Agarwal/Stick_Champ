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
        //System.out.println(playerxcoord+" "+playerycoord);
        this.stick = new Rectangle(playerxcoord+63,460, WIDTH,0);
        this.stick.setFill(Color.BLACK);
    }

    public Rectangle getstick(){
        return this.stick;
    }

    public void increment_size() throws InterruptedException {
        this.height+=10;
        stick.setHeight(stick.getHeight()+10);
        stick.setY(stick.getY()-10);           //Must move stick upwards too else size grows downwards
        sleep(120);
    }

    public void rotate(Player player,int mount_startcoord,int mount_width,double red_target_start,Cherry cherry,Stick stick,Mountain targetmount){
        TranslateTransition trans = new TranslateTransition(Duration.millis(1000),this.stick);
        trans.setByY((double)this.height/2+3);
        trans.setByX((double)this.height/2+5);
        RotateTransition rot = new RotateTransition(Duration.millis(1000),this.stick);
        rot.setByAngle(90);
        rot.play();
        trans.play();
        trans.setOnFinished(event->{
            player.move(this.height,mount_startcoord,mount_startcoord+mount_width,red_target_start,cherry,stick,targetmount);
        });
    }

    public double getlength(){
        return this.height;
    }

    public void setskin(){}
}
