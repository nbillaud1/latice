package latice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LaticeAppSb extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("view/GameView.fxml"));
		
		/* Mise en place de la page */
		StackPane root = new StackPane();
		root.getChildren().add(loader.load());
		
		Scene scene = new Scene(root,1000,1000);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Jeu du Latice");
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
