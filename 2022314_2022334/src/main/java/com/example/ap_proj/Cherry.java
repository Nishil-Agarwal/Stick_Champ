package com.example.ap_proj;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Random;

public class Cherry{
    private final int HEIGHT=35;
    private final int WIDTH=45;
    private ImageView cherry;

    private int xcoord;
    private int ycoord;
    private int visibile=0;

    Cherry() throws FileNotFoundException {
        Image cherries=new Image(new FileInputStream("src\\main\\resources\\com\\example\\ap_proj\\Cherry.png"));
        cherry = new ImageView(cherries);
        cherry.setFitHeight(this.HEIGHT);
        cherry.setFitWidth(this.WIDTH);
    }

    private double rarity_calc(int score){
        if (score>750){
            return 15;
        }else if(score<150){
            return 50;
        }else{
            return 65-score/11;
        }
    }

    private ImageView generate_cherry(int mountain_start){
        this.visibile=1;
        if (mountain_start>125){
            this.cherry.setX(new Random().nextInt(80,mountain_start-45));
            this.cherry.setY(460+(new Random().nextInt(0,1))*42);
            this.xcoord=(int) this.cherry.getX();
            this.ycoord=(int) this.cherry.getY();
            return this.cherry;
        }
        return null;
    }

    public ImageView try_generation(int score,int mountain_start){
        double chance=rarity_calc(score);
        //System.out.println(chance);
        double number=new Random().nextDouble(0,100);
        if (number>chance){
            return generate_cherry(mountain_start);
        }
        return null;                       //Handle in controller code
    }

    public void cherry_collected(){
        this.visibile=0;
        Pane root=((Pane)(this.cherry.getScene().getRoot()));
        root.getChildren().remove(cherry);
    }

    public int getxcoord(){
        return this.xcoord;
    }

    public int getycoord(){
        return this.ycoord;
    }

    public int getvisibility(){
        return this.visibile;
    }
}