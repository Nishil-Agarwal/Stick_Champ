package com.example.ap_proj;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Main extends Application {
    private Stage stage;
    private Scene scene;   //gotta remove this temp placeholder screen after deadline 1
    //just generate new scene by saved score etc. once code is completed and user presses any buttons that require to go back to gamescene
    private Player player;
    private Mountain mount0;
    private Mountain mount1;
    private Mountain mount2;
    private Mountain mount3;
    //private ImageView backg_img;
    private Stick mystick;
    private long stickstart;
    private long endtime;

    public void switchToGameScene(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scenenew= new Scene(root, 600, 325);
        addobjs(scenenew);
        stage.setScene(scenenew);
        stage.show();
    }

    public void switchToStartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent event){
        Alert exitbox = new Alert(Alert.AlertType.CONFIRMATION);
        exitbox.setTitle("Exit");
        exitbox.setHeaderText("You're about to exit the Game");
        exitbox.setContentText("Do you want to exit the Game?");
        if(exitbox.showAndWait().get()== ButtonType.OK){
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
            System.out.println("You have exited the Game.");
        }
    }

    public void switchToEndScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EndScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initializeobjs() throws FileNotFoundException {
        this.player=new Player();
        this.mount0=new Mountain(0);
        this.mount1=new Mountain(200);
        this.mount2=new Mountain(450);
        this.mount3=new Mountain(600);
    }
    public void addobjs(Scene scene) throws FileNotFoundException {
//        player=new Player();
//        mount0=new Mountain(0);
//        mount1=new Mountain(200);
//        mount2=new Mountain(450);
//        mount3=new Mountain(600);
        initializeobjs();

        Pane root=(Pane)scene.getRoot();

        root.getChildren().add(player.getplyr());
        root.getChildren().add(mount0.getmount());
        root.getChildren().add(mount1.getmount());
        root.getChildren().add(mount2.getmount());
        root.getChildren().add(mount3.getmount());

    }

    public void generatecherry(){}
    public void generatemountains(){}
    public void revive(){}

    @Override
    public void start(Stage primarystage) throws IOException, InterruptedException {
        this.stage=primarystage;
//        player=new Player();
//        mount0=new Mountain(0);
//        mount1=new Mountain(200);
//        mount2=new Mountain(450);
//        mount3=new Mountain(600);
//        //Player player2=new Player();
//        initializeobjs();

        FXMLLoader loadhome = new FXMLLoader(getClass().getResource("StartScene.fxml"));
        Pane roothome = loadhome.load();
        Scene scenehome = new Scene(roothome, 600, 325);


//        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
//        Pane root = loader.load();

        //backg_img = new ImageView(new Image("file:C:\\Users\\nishi\\Vs_Code_Projects\\IIITD\\SEM_3\\AP\\Assignments\\AP_Proj\\src\\main\\resources\\com\\example\\ap_proj\\projimg.jpg"));

        //root.getChildren().add(backg_img);
//        root.getChildren().add(player.getplyr());
//        root.getChildren().add(mount0.getmount());
//        root.getChildren().add(mount1.getmount());
//        root.getChildren().add(mount2.getmount());
//        root.getChildren().add(mount3.getmount());
//
//        tempgamescene = new Scene(root, 600, 325);  //Make it local here by inserting a "Scene" at start of line firstly prolly to revert back

//        tempgamescene.setOnMousePressed(e -> {
//            if (e.isPrimaryButtonDown()){
//                stickstart=System.currentTimeMillis();
//            }
//        });
//
//        tempgamescene.setOnMouseReleased(e->{
//            endtime=System.currentTimeMillis();
//            Point2D playercoords=player.getplyr().localToScene(player.getplyr().getLayoutX(), player.getplyr().getLayoutY());
//            double xcoord = playercoords.getX();
//            double ycoord = playercoords.getY();
//            mystick=new Stick(xcoord,ycoord,(int)(endtime-stickstart)/6,player);
//            root.getChildren().add(mystick.getstick());
//        });
//
//        tempgamescene.setOnKeyReleased(e -> {
//            switch (e.getCode()) {
//                case S:
//                    player.flip();
//                    break;
//                case J:
//                    player.move();
//                    break;
//            }
//        });

        primarystage.setTitle("Stick Hero");
        primarystage.setScene(scenehome);
        primarystage.show();
    }

    class Player{
        private int height = 60;
        private int width = 50;
        private ImageView plyr;
        private int flipstatus=0;
        private int score;
        private int cherries;
        private int coins;
        Player() throws FileNotFoundException {
            //plyr= new Rectangle(20,200, 30,40);
            Image character=new Image(new FileInputStream("C:\\Users\\nishi\\Vs_Code_Projects\\IIITD\\SEM_3\\AP\\Assignments\\AP_Proj\\src\\main\\java\\com\\example\\ap_proj\\Stickman.png"));
            plyr = new ImageView(character);
            plyr.setFitHeight(this.height);
            plyr.setFitWidth(this.width);
            plyr.setX(20);
            plyr.setY(200);
        }
        public ImageView getplyr(){
            return this.plyr;
        }
        public void move(){
            TranslateTransition movepath = new TranslateTransition(Duration.millis(2500), this.plyr);
            movepath.setToX(500);
            movepath.play();
        }

        public void flip() {
            RotateTransition flip=new RotateTransition(Duration.millis(200),this.plyr);
            flip.setByAngle(180);
            if (flipstatus==0){
                flipstatus=1;
                TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.plyr);
                flipping.setToY(this.height);
                flip.play();
                flipping.play();
            }else if (flipstatus==1){
                flipstatus=0;
                TranslateTransition flipping = new TranslateTransition(Duration.millis(200), this.plyr);
                flipping.setToY(0);
                flip.play();
                flipping.play();
            }
            //return;
        }

        public void falldown(){}
        public void reviving_ritual(){}
        public void cherrygained(){}
        public void coinsgained(){}
        public void setscore(){}
    }
    
    public class Redtarget{
        private final int height=3;
        private final int length=5;
        private Rectangle target;
        public Redtarget(){}
    }

    public class Mountain {
        private int baseHeight = 240;
        private int baseWidth = 80;
        private Rectangle mountain;
        private Redtarget target;

        public Mountain(int xCoord) {
            mountain = new Rectangle(xCoord, 260, baseWidth, baseHeight);

            try {
                //Image image = new Image("file:C:\\Users\\nishi\\Vs_Code_Projects\\IIITD\\SEM_3\\AP\\Assignments\\AP_Proj\\src\\main\\java\\com\\example\\ap_proj\\mount.jpg");
                this.mountain.setFill(Color.SADDLEBROWN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        public void createtarget(){}
        public void gettarget(){}
        public Rectangle getmount() {
            return this.mountain;
        }
    }

    public class Stick{
        private final int width =5;
        private int length;
        private Rectangle stick;
        private ImageView skin;
        public Stick(double playerxcoord,double playerycoord,int length,Player player){
            this.length=length;
            stick = new Rectangle(playerxcoord+70,playerycoord+255,length, width);
            this.stick.setFill(Color.DARKORANGE);
        }

        public Rectangle getstick(){
            return this.stick;
        }

        public void setskin(){}
    }

    public class Cherry{
        private final int lenght=20;
        private final int width=20;
        private Rectangle cherry;
        public Cherry(){}
        private int rarity_calc(int score){return 0;}
        private void setvisibility(){};
    }




    public static void main(String[] args) {
        launch(args);
    }
}
