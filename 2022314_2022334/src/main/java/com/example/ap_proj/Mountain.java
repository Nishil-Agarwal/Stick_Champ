package com.example.ap_proj;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Mountain {
    private int height = 135;
    private Rectangle mountain;
    private int xcoord;
    private int width;

    private Redtarget target;

    public Mountain(int xcoord,int width) {
        if (xcoord!=-1){
            this.xcoord=xcoord;
            this.width=width;
            //System.out.println(xcoord);
            mountain = new Rectangle(600, 465, width, height);
            this.mountain.setFill(Color.BLACK);
        }else{
            this.width=new Random().nextInt(25,150);
            int maxstartpoint=600-this.width;
            this.xcoord=new Random().nextInt(100,maxstartpoint);
            mountain = new Rectangle(600, 465, this.width, height);
            this.mountain.setFill(Color.BLACK);
        }
        createtarget();
    }

    public int getMountainStart(){
        return this.xcoord;
    }

    public int getMountainWidth(){
        return this.width;
    }

    public Rectangle getmountain() {
        return this.mountain;
    }

    private void createtarget(){
        this.target=new Redtarget(this.xcoord+this.width/2);
    }
    public Redtarget gettarget(){
        return this.target;
    }

    public class Redtarget{
        private final int HEIGHT=5;
        private final int WIDTH=12;
        private double start_xcoord;
        private Rectangle target;

        public Redtarget(double xcoord){          //xcoord to be of center of mountain
            this.start_xcoord=xcoord-6;
            this.target = new Rectangle(600,462.5,WIDTH,HEIGHT);     //2.5 may cause issue due to decimal
            this.target.setFill(Color.RED);
        }

        public Rectangle getrect(){
            return this.target;
        }

        public double start_xcoord(){
            return start_xcoord;
        }

        public void movein2(){
            TranslateTransition movein2=new TranslateTransition(Duration.millis(1000),this.target);
            movein2.setToX(this.start_xcoord-600);
            movein2.play();
        }
    }

    public void moveout(){
        TranslateTransition moveout=new TranslateTransition(Duration.millis(1000), this.mountain);
        TranslateTransition moveout2=new TranslateTransition(Duration.millis(1000), this.target.getrect());
        moveout.setByX(-1000);
        moveout2.setByX(-1000);
        Pane root=((Pane)(this.mountain.getScene().getRoot()));
        moveout.setOnFinished(event->{
            root.getChildren().remove(this.mountain);
        });
        moveout2.setOnFinished(event2->{
            root.getChildren().remove(target.getrect());
        });
        moveout.play();
        moveout2.play();
    }

    public void movein(){
        TranslateTransition movein1=new TranslateTransition(Duration.millis(1000),this.mountain);
        movein1.setToX(this.xcoord-600);
        this.target.movein2();
        movein1.play();
    }
}