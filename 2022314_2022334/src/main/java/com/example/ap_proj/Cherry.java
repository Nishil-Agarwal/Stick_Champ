package com.example.ap_proj;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Random;

public class Cherry{
    private final int HEIGHT=35;
    private final int WIDTH=45;
    private ImageView cherry;

    Cherry() throws FileNotFoundException {
        Image cherries=new Image(new FileInputStream("src\\main\\resources\\com\\example\\ap_proj\\Cherry.png"));
        cherry = new ImageView(cherries);
        cherry.setFitHeight(this.HEIGHT);
        cherry.setFitWidth(this.WIDTH);
    }

    private double rarity_calc(int score){
        if (score>750){
            return 10;
        }else if(score<150){
            return 50;
        }else{
            return 65-score/11;
        }
    }

    private ImageView generate_cherry(){
        this.cherry.setX(new Random().nextInt(100,600));
        this.cherry.setY(210+(new Random().nextInt(0,1))*42);
        return this.cherry;
    }

    public ImageView try_generation(int score){
        double chance=rarity_calc(score);
        double number=new Random().nextDouble(0,100);
        if (number>chance){
            return generate_cherry();
        }
        return null;                       //Handle in controller code
    }
}
