package com.example.ap_proj;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static java.lang.Math.pow;

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
        return (100/(pow(3,-score)));
    }

    public ImageView generate_cherry(){
        return this.cherry;
    }
}
