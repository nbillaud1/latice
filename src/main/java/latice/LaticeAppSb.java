package latice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LaticeAppSb extends Application{
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("view/GameView.fxml"));
		
		/*Création de la grille*/
		GridPane grid = new GridPane();
		grid.setHgap(0.2);
        grid.setVgap(0.2);
        grid.setAlignment(Pos.CENTER);
        
  
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
            	Rectangle square = createSquare();
            	if((row == 0 && col == 0) || (row == 0  && col == 4) || (row == 0  && col == 8) 
            			|| (row == 1  && col == 1) || (row == 1  && col == 7) || (row == 2  && col == 2) 
            			|| (row == 2  && col == 6) || (row == 4  && col == 0) || (row == 4  && col == 8)
            			|| (row == 6  && col == 2) || (row == 6  && col == 6) || (row == 7  && col == 1)
            			|| (row == 7  && col == 7) || (row == 8  && col == 0) || (row == 8  && col == 8)){
            		Image sun = createSun();
            		ImageView imvSun = new ImageView(sun);
            		imvSun.fitWidthProperty().bind(square.widthProperty().multiply(0.9));
                    imvSun.fitHeightProperty().bind(square.heightProperty().multiply(0.9));
                    imvSun.setPreserveRatio(true);
            		StackPane cell = new StackPane();
                    cell.getChildren().addAll(square,imvSun);
                    grid.add(cell, col, row);
            	}
            	else if(row == 4 && col == 4){
            		Image moon = createMoon();
            		ImageView imvMoon = new ImageView(moon);
            		imvMoon.fitWidthProperty().bind(square.widthProperty().multiply(0.9));
                    imvMoon.fitHeightProperty().bind(square.heightProperty().multiply(0.9));
                    imvMoon.setPreserveRatio(true);
                    StackPane cell = new StackPane();
                    cell.getChildren().addAll(square,imvMoon);
                    grid.add(cell, col, row);
            	}
            	else {
            		grid.add(square, col, row);
            	}
            }
        }
        
        /*Création du rack*/
        GridPane rack = new GridPane();
        for (int col = 0; col < 5; col++) {
        	Rectangle square = createSquare();
        	rack.add(square, col, 0);
        }
        rack.setAlignment(Pos.CENTER);
        rack.setHgap(5);
        
		/* Mise en place de la page */
        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(rack);
        root.setPadding(new javafx.geometry.Insets(0, 0, 30, 0));
		Scene scene = new Scene(root,1200,900);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Jeu du Latice");
		primaryStage.show();
	}
	
	private Rectangle createSquare() {
		Rectangle square = new Rectangle(50, 50, Color.WHITE);
        square.setStroke(Color.BLACK);
        return square;
	}
	
	private Image createSun() {
		Image sun = new Image(getClass().getResource("/latice/image/sun.png").toExternalForm());
		return sun;
	}
	
	private Image createMoon() {
		Image moon = new Image(getClass().getResource("/latice/image/moon.png").toExternalForm());
		return moon;
	}
	

	public static void main(String[] args) {
		Application.launch(args);
	}
}
