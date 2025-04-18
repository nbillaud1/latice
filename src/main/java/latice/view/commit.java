package latice.view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class commit extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Bonjour depuis JavaFX avec Maven !");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("JavaFX + Maven");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ✅ C'est cette méthode que Java cherche :
    public static void main(String[] args) {
        launch(args); // appelle start() automatiquement
    }
}