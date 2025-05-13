package latice.audio;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {
    private static MediaPlayer currentPlayer;

    public static void play(String resourcePath) {
        stop();

        URL music = MusicManager.class.getResource(resourcePath);
        Media media = new Media(music.toExternalForm());
        currentPlayer = new MediaPlayer(media);
        currentPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        currentPlayer.setOnReady(() -> currentPlayer.play());
    }

    public static void stop() {
        if (currentPlayer != null) {
            currentPlayer.stop();
            currentPlayer.dispose();
            currentPlayer = null;
        }
    }
}