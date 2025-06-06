package latice.audio;

import java.net.URL;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {
    private static MediaPlayer currentPlayer;
    public static final AudioClip DROPSOUND = new AudioClip(MusicManager.class.getResource("/latice/sound/Pop.wav").toExternalForm());

    public static void play(String resourcePath) {
        stop();

        URL music = MusicManager.class.getResource(resourcePath);
        Media media = new Media(music.toExternalForm());
        currentPlayer = new MediaPlayer(media);
        currentPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        currentPlayer.setVolume(0.2);
        currentPlayer.setOnReady(() -> currentPlayer.play());
    }

    public static void stop() {
        if (currentPlayer != null) {
            currentPlayer.stop();
            currentPlayer.dispose(); //Sert à libérer l'espace utilisé par l'audio en cour
            currentPlayer = null;
        }
    }
}