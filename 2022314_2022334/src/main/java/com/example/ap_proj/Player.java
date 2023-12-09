package com.example.ap_proj;

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Player{
    private final int height = 60;
    private final int width = 50;
    private ImageView sprite_image;
    private boolean flipped=false;        //false = upright

    private Label incremented;
    private Label totalsc1;
    private Label cherries1;
    private int score;
    private int cherries;
    private int completion_status=0;
    private int death;
    private int dead;
    private final Streams stream=new Streams();

    private static Player player= null;

    public static Player getInstance() throws FileNotFoundException {
        if(player==null){
            player= new Player();
        }
        return player;
    }

    private Player() throws IOException {
        Image character=new Image(new FileInputStream("src\\main\\resources\\com\\example\\ap_proj\\Stickman.png"));
        this.score=stream.readScore();
        this.cherries=stream.readCherry();
        sprite_image = new ImageView(character);
        sprite_image.setFitHeight(this.height);
        sprite_image.setFitWidth(this.width);
        sprite_image.setX(20);
        sprite_image.setY(405);
    }

    public void setlabels(Pane root) throws IOException {
        this.totalsc1=new Label(String.valueOf(stream.readScore()));
        this.cherries1=new Label(String.valueOf(stream.readCherry()));

        totalsc1.setFont(Font.font("Arial Black",16));
        totalsc1.setTextFill(Color.WHITE);
        totalsc1.setLayoutX(537);
        totalsc1.setLayoutY(40);
        cherries1.setFont(Font.font("Arial Black",16));
        cherries1.setTextFill(Color.WHITE);
        cherries1.setLayoutX(59);
        cherries1.setLayoutY(40);

        root.getChildren().add(totalsc1);
        root.getChildren().add(cherries1);
    }

    public ImageView getplayer(){
        return this.sprite_image;
    }

    public void move(double distance,int target_mountain_start, int target_mountain_finish,double red_target_start,Cherry cherry,Stick stick,Mountain targetmount){
        TranslateTransition movepath = new TranslateTransition(Duration.millis(distance*9), this.sprite_image);
        movepath.setByX(distance+40);

        AnimationTimer cherry_check_timer= new AnimationTimer(){
            @Override
            public void handle(long l) {
                double sprite_xcoord = sprite_image.localToScene(sprite_image.getLayoutX(), sprite_image.getLayoutY()).getX();
                if (cherry.getvisibility()!=0){
                    //System.out.println(flipped+" "+cherry.getycoord());
                    if (sprite_xcoord+25>cherry.getxcoord() && sprite_xcoord+25<(cherry.getxcoord()+45)){
                        if ((cherry.getycoord()==467 && flipped) || (cherry.getycoord()==425 && !flipped)){
                            cherry.cherry_collected();
                            cherries+=1;
                            try {
                                setCherries_onboard();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        };

        movepath.setOnFinished(checkfall->{
            cherry_check_timer.stop();
            double top_left_xcoord=sprite_image.localToScene(sprite_image.getLayoutX(), sprite_image.getLayoutY()).getX();
            double xcoord=top_left_xcoord+10+sprite_image.getX();
//            System.out.println(distance);
//            System.out.println(top_left_xcoord);
//            System.out.println(xcoord);
//            System.out.println(target_mountain_start);
            if (xcoord>target_mountain_finish || xcoord<target_mountain_start){
                setDeadStatus();
                falldown();
            }else{
                if (xcoord>red_target_start && xcoord<(red_target_start+12)){
                    try {
                        moveback(90,stick,targetmount);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    try {
                        moveback(40,stick,targetmount);
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        movepath.play();
        cherry_check_timer.start();
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
            RotateTransition fall=new RotateTransition(Duration.millis(500),this.sprite_image);
            fall.setByAngle(90);
            if (!flipped){
                TranslateTransition falling = new TranslateTransition(Duration.millis(500), this.sprite_image);
                falling.setByY(200);
                fall.play();
                falling.setOnFinished(e2->{
                    this.death=1;});
                falling.play();
            }else{
                TranslateTransition flipping = new TranslateTransition(Duration.millis(500), this.sprite_image);
                flipping.setByY(160);
                flipping.setOnFinished(e2->{
                    this.death=1;});
                flipping.play();
            }
        });
    }

    public void moveback(int incrementscore,Stick stick,Mountain targetmount) throws InterruptedException, IOException {
        this.score+=incrementscore;
        TranslateTransition back = new TranslateTransition(Duration.millis(1000), this.sprite_image);
        back.setToX(0);
        back.play();
        setscore(incrementscore,stick,targetmount);
    }

    public void setDeath(){
        this.death=0;
    }

    public int getDeadStatus(){
        return this.dead;
    }

    public void setDeadStatus(){
        this.dead=1;
    }

    public int getDeath(){
        return this.death;
    }

    public void reviving_ritual() throws IOException {
        this.cherries-=5;
        this.dead=0;
        stream.writeCherry(this.cherries);
    }

    public int getcherries(){
        return this.cherries;
    }

    public int getscore(){
        return this.score;
    }

    public void setscore(int latestscore,Stick stick,Mountain targetmount) throws InterruptedException, IOException {
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
        stream.writeScore(this.score);
        FadeTransition fade=new FadeTransition(Duration.millis((700)),totalsc1);
        fade.setOnFinished(e->{
            root.getChildren().remove(incremented);
//            try {
//                sleep(500);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
            //code for mountain move
            root.getChildren().remove(stick.getstick());
            targetmount.moveout();
            this.completion_status=1;
        });
        fade.play();
    }

    public int completedCheck(){
        return completion_status;
    }

    public void setCompleted(int num){
        this.completion_status=num;
    }

    public void setCherries_onboard() throws IOException {
        Pane root=((Pane)(this.sprite_image.getScene().getRoot()));
        root.getChildren().remove(cherries1);

        this.cherries1=new Label(String.valueOf(this.cherries));

        cherries1.setFont(Font.font("Arial Black",16));
        cherries1.setTextFill(Color.WHITE);
        cherries1.setLayoutX(59);
        cherries1.setLayoutY(40);

        root.getChildren().add(cherries1);
        stream.writeCherry(this.cherries);
    }

}
