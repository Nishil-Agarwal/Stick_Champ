package com.example.ap_proj;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class MusicController {
    MediaPlayer mediaPlayer;

    public void music() {

        Media h = new Media(new File("C:\\Users\\nishi\\Vs_Code_Projects\\IIITD\\SEM_3\\AP\\Assignments\\2022314_2022334\\src\\main\\java\\com\\example\\ap_proj\\Equilibrium_-_Ron_Gelinas.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();

    }
}