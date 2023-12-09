package com.example.ap_proj;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class MusicController {
    MediaPlayer mediaPlayer;

    public void music() {

        Media h = new Media(new File("com/example/ap_proj/Robbero_-_After_Hours.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();

    }
}
