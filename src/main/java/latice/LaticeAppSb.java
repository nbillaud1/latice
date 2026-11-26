package latice;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import latice.audio.MusicManager;
import latice.controller.GameViewController;
import latice.view.Icons;
import latice.view.ScaledLayout;

public class LaticeAppSb extends Application{

	private static final String STYLE = "/latice/view/style.css";
	private Button btnStart;
	private Button btnBackground;
	private Button btnRules;
	private Button btnOk;
	private Button btnQuit;
	private Button btnOptions;
	private Label lblErrorStart;
	private Label lblBackgPath;
	private TextField tfPlayer1Name;
	private TextField tfPlayer2Name;
	private Stage primaryStage;
	private Stage options;
	private Stage rulesWindow;
	private String imgBackground = null; //null = océan animé
	private int turnNumber = 10;

	//Fenêtre redimensionnable, ouverte en plein écran (le contenu s'adapte via ScaledLayout)
	private void showResizable(Stage stage, ScaledLayout root, String title) {
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		Scene scene = new Scene(root, screen.getWidth() * 0.9, screen.getHeight() * 0.9);
		scene.getStylesheets().add(getClass().getResource(STYLE).toExternalForm());
		stage.setScene(scene);
		stage.setTitle(title);
		stage.setMinWidth(720);
		stage.setMinHeight(560);
		stage.setMaximized(true);
		stage.show();
	}

	//Bouton rond avec une icône vectorielle
	private static Button iconButton(String iconPath) {
		Button button = new Button();
		button.setGraphic(Icons.icon(iconPath, 26));
		button.getStyleClass().add("icon-button");
		return button;
	}

	//Fait flotter un élément de haut en bas comme une bouée
	private static void bob(javafx.scene.Node node, double amplitude, double seconds, double delay) {
		TranslateTransition bob = new TranslateTransition(Duration.seconds(seconds), node);
		bob.setFromY(-amplitude);
		bob.setToY(amplitude);
		bob.setAutoReverse(true);
		bob.setCycleCount(Animation.INDEFINITE);
		bob.setInterpolator(Interpolator.EASE_BOTH);
		bob.setDelay(Duration.seconds(delay));
		bob.play();
	}

	@Override
	public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/latice/image/icon.png")));
        ButtonListener lstnBtn = new ButtonListener();

        //Logo, slogan et les six animaux du jeu
        Label logo = new Label("LATICE");
        logo.getStyleClass().add("logo");
        bob(logo, 5, 2.2, 0);
        Label tagline = new Label("Le jeu de tuiles du lagon");
        tagline.getStyleClass().add("tagline");
        HBox tilesRow = new HBox(16);
        tilesRow.setAlignment(Pos.CENTER);
        String[] tiles = {"turtle_t", "dolphin_n", "bird_y", "flower_m", "gecko_g", "feather_r"};
        for (int i = 0; i < tiles.length; i++) {
        	ImageView tile = new ImageView(new Image("/latice/image/" + tiles[i] + ".png", 64, 64, true, true));
        	tile.setRotate(i % 2 == 0 ? -6 : 6);
        	tile.setStyle("-fx-effect: dropshadow(gaussian, rgba(11,57,84,0.5), 8, 0.2, 0, 3);");
        	bob(tile, 6, 1.6, i * 0.25);
        	tilesRow.getChildren().add(tile);
        }

		//Saisie des noms
		this.tfPlayer1Name = new TextField();
		this.tfPlayer1Name.setPromptText("Nom du joueur 1");
		this.tfPlayer2Name = new TextField();
		this.tfPlayer2Name.setPromptText("Nom du joueur 2");
		HBox hBoxNames = new HBox(24, nameField("Joueur 1", tfPlayer1Name), nameField("Joueur 2", tfPlayer2Name));
		hBoxNames.setAlignment(Pos.CENTER);

        this.lblErrorStart = new Label();
        this.lblErrorStart.getStyleClass().add("error-label");
        this.lblErrorStart.setMinHeight(22);

		this.btnStart = new Button("Commencer la partie");
		this.btnStart.getStyleClass().add("cta");
		this.btnStart.setDefaultButton(true); //Entrée lance aussi la partie
		this.btnStart.setOnAction(e -> tryStart());

		VBox card = new VBox(14, hBoxNames, lblErrorStart, btnStart);
		card.getStyleClass().add("panel");
		card.setAlignment(Pos.CENTER);
		card.setMaxWidth(640);

		VBox center = new VBox(14, logo, tagline, tilesRow, card);
		VBox.setMargin(card, new Insets(36, 0, 0, 0));
		center.setAlignment(Pos.CENTER);
		//Taille de référence du menu : il est agrandi ou réduit pour remplir la fenêtre
		StackPane menu = new StackPane(center);
		menu.setPrefSize(1000, 860);

		//Options et quitter en haut à droite
		btnOptions = iconButton(Icons.SETTINGS);
		btnOptions.setOnMouseClicked(lstnBtn);
		btnQuit = iconButton(Icons.CLOSE);
		btnQuit.getStyleClass().add("danger");
		btnQuit.setOnMouseClicked(lstnBtn);
		HBox topButtons = new HBox(12, btnOptions, btnQuit);
		topButtons.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		StackPane.setAlignment(topButtons, Pos.TOP_RIGHT);
		StackPane.setMargin(topButtons, new Insets(24));

		Label team = new Label("by Latice Team");
		team.getStyleClass().add("footer");
		StackPane.setAlignment(team, Pos.BOTTOM_CENTER);
		StackPane.setMargin(team, new Insets(0, 0, 26, 0));

		ScaledLayout root = new ScaledLayout(menu, true, 1.35);
		root.getChildren().addAll(topButtons, team);

        //Music du lobby
        MusicManager.play("/latice/sound/Lobby_music.wav");

        showResizable(primaryStage, root, "Latice");
        tfPlayer1Name.requestFocus();
	}

	private VBox nameField(String title, TextField field) {
		Label label = new Label(title);
		label.getStyleClass().add("field-label");
		field.setPrefWidth(250);
		return new VBox(6, label, field);
	}

	private void tryStart() {
		if(tfPlayer1Name.getText().equals("")) {
			lblErrorStart.setText("Vous devez renseigner un nom pour le joueur 1.");
		}
		else if(tfPlayer2Name.getText().equals("")) {
			lblErrorStart.setText("Vous devez renseigner un nom pour le joueur 2.");
		}
		else if(tfPlayer2Name.getText().equals(tfPlayer1Name.getText())) {
			lblErrorStart.setText("Les noms doivent être différents");
		}
		else if(tfPlayer2Name.getLength() > 10 || tfPlayer1Name.getLength() > 10) {
			lblErrorStart.setText("10 caractères maximum par noms");
		}
		else {
			lblErrorStart.setText("");
			try {
				startTheGame(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void showOptions() {
		options = new Stage();
		options.initModality(Modality.APPLICATION_MODAL); // Bloque la fenêtre principale
		options.initOwner(primaryStage);
		options.setResizable(false);
		options.initStyle(StageStyle.TRANSPARENT);
        options.getIcons().add(new Image(getClass().getResourceAsStream("/latice/image/icon.png")));

        ButtonListener lstnBtn = new ButtonListener();
        Label lblOptions = new Label("Options");
        lblOptions.getStyleClass().add("section-title");

  		//Choix du nombre de tours
  		ComboBox<Integer> cbTurnNbr = new ComboBox<>();
        cbTurnNbr.getItems().addAll(10,12,14,16,18,20);
        cbTurnNbr.setValue(turnNumber);
        cbTurnNbr.setOnAction(e -> {
        	turnNumber = cbTurnNbr.getValue();
        });

        //Choix du fond
	    this.lblBackgPath = new Label(backgroundName());
		btnBackground = new Button("Changer");
		btnBackground.setOnMouseClicked(lstnBtn);

        //Bouton pour afficher les règles
      	btnRules = new Button("Lire les règles");
      	btnRules.setGraphic(Icons.icon(Icons.BOOK, 20));
      	btnRules.setOnMouseClicked(lstnBtn);

        btnOk = new Button("Valider");
        btnOk.getStyleClass().add("cta");
        btnOk.setOnMouseClicked(lstnBtn);

        VBox rows = new VBox(18,
        		optionRow("Nombre de tours", cbTurnNbr),
        		optionRow("Fond du plateau", backgroundRow()),
        		optionRow("Règles du jeu", btnRules));

		VBox root = new VBox(26, lblOptions, rows, btnOk);
		root.getStyleClass().add("option-root");
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(30, 40, 30, 40));

        Scene scene = new Scene(root, 600, 420);
        scene.getStylesheets().add(getClass().getResource(STYLE).toExternalForm());
        scene.setFill(Color.TRANSPARENT); // Pour que l'on voit les bords arrondis
        options.setScene(scene);
        options.showAndWait();
	}

	//Ligne d'option : intitulé à gauche, réglage à droite
	private HBox optionRow(String title, javafx.scene.Node control) {
		Label label = new Label(title);
		label.getStyleClass().add("field-label");
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		HBox row = new HBox(label, spacer, control);
		row.setAlignment(Pos.CENTER_LEFT);
		return row;
	}

	private HBox backgroundRow() {
		HBox row = new HBox(12, lblBackgPath, btnBackground);
		row.setAlignment(Pos.CENTER_RIGHT);
		return row;
	}

	private String backgroundName() {
		return imgBackground == null ? "Océan animé" : imgBackground;
	}

	private void showRules() {
        rulesWindow = new Stage();
        rulesWindow.initModality(Modality.APPLICATION_MODAL);
        rulesWindow.initOwner(options);
        rulesWindow.setResizable(false);
        rulesWindow.getIcons().add(new Image(getClass().getResourceAsStream("/latice/image/icon.png")));
        rulesWindow.initStyle(StageStyle.TRANSPARENT);

        Label rulesTitle = new Label("Règles du jeu");
        rulesTitle.getStyleClass().add("section-title");
        Button btnClose = iconButton(Icons.CLOSE);
        btnClose.setOnAction(e -> rulesWindow.close());
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox header = new HBox(rulesTitle, spacer, btnClose);
        header.setAlignment(Pos.CENTER_LEFT);

        Label rules = new Label(
                "Le Latice est un jeu de stratégie où vous devez associer des tuiles de la même "
                + "couleur ou de la même forme.\n\n"
                + "DISTRIBUTION\n"
                + "Au début de la partie, chaque joueur possède une pioche de 31 tuiles plus une main de 5 tuiles. "
                + "Il existe seulement deux exemplaires de chaque tuile.\n\n"
                + "VICTOIRE\n"
                + "Le gagnant est celui qui se débarrasse de toutes ses tuiles ou, dans notre application, "
                + "celui qui a le moins de tuiles à la fin du nombre de tours impartis (10 minimum).\n\n"
                + "DÉROULEMENT\n"
                + "La première tuile doit être placée sur la lune au centre. Ensuite, une tuile ne peut être posée "
                + "que si elle touche par un côté une tuile déjà posée, et qu'elle est de la même forme ou de la même "
                + "couleur que chacune de ses voisines (les diagonales ne comptent pas).\n\n"
                + "À chaque tour, le joueur dispose d'une action :\n"
                + "  •  poser une tuile ;\n"
                + "  •  changer toute sa main (cela passe son tour) ;\n"
                + "  •  passer son tour.\n"
                + "S'il pose une tuile, il peut acheter une action supplémentaire avec ses pierres.\n\n"
                + "LES PIERRES\n"
                + "  •  2 tuiles voisines : un Double, qui rapporte une demi-pierre.\n"
                + "  •  3 tuiles voisines : un Trèfle, qui rapporte une pierre soleil.\n"
                + "  •  4 tuiles voisines : un Latice, qui rapporte deux pierres soleil.\n"
                + "  •  Poser sur un soleil rapporte une pierre soleil.\n"
                + "Une action supplémentaire coûte une pierre soleil ou deux demi-pierres. Les demi-pierres ne sont "
                + "pas limitées, mais au-delà de 3 pierres soleil en fin de tour, l'excédent est retiré.");
        rules.getStyleClass().add("rules-text");
        rules.setWrapText(true);
        rules.setPrefWidth(610);

        ScrollPane scrollPane = new ScrollPane(rules);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox root = new VBox(14, header, scrollPane);
        root.getStyleClass().add("rules-root");

        Scene scene = new Scene(root, 700, 620);
        scene.getStylesheets().add(getClass().getResource(STYLE).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        rulesWindow.setScene(scene);
        rulesWindow.showAndWait(); // Affiche la fenêtre et attend sa fermeture
    }

	private class ButtonListener implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			if(event.getSource() == btnOptions) {
				showOptions();
			}
			else if(event.getSource() == btnRules) {
				showRules();
			}
			else if(event.getSource() == btnOk) {
				options.close();
			}
			else if(event.getSource() == btnBackground) {
				FileChooser fileChooser = new FileChooser();
			      fileChooser.setTitle("Choisir une image provenant de : latice/image/ ");
			      File selectedFile = fileChooser.showOpenDialog(options);
			      if (selectedFile != null) {
			        imgBackground = selectedFile.getName();
			        lblBackgPath.setText(backgroundName());
			      }
			}
			else if(event.getSource() == btnQuit) {
				Platform.exit();
			}
		}
	}

	public void startTheGame(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/latice/view/GameView.fxml"));
		StackPane board = loader.load();

		GameViewController controller = loader.getController();
		controller.setRoundConter(turnNumber);
	    controller.setPlayer1Name(this.player1Name()); // on fournit les noms des joueurs au controller.
	    controller.setPlayer2Name(this.player2Name());
	    controller.initialize();

		Stage stage = new Stage();
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/latice/image/icon.png")));
		ScaledLayout root = new ScaledLayout(board, imgBackground == null, 1.6);
		root.setOnOrientationChange(controller::applyLayout); //plateau à gauche en paysage, HUD dessous en portrait

		//Définit le background : l'océan animé par défaut, sinon l'image choisie dans les options
		try {
			if (imgBackground != null) {
		      	Image image = new Image("/latice/image/" + imgBackground);
		        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		        root.setBackground(new Background(backgroundImage));
			}
			showResizable(stage, root, "Latice");

			//Music du plateau
			MusicManager.play("/latice/sound/Latice_theme.wav");

			primaryStage.close();
		}
		catch(IllegalArgumentException e) {
			lblErrorStart.setText("L'image de fond selectionnée n'est pas valide.");
		}

    }

	public int turnNumber() {
		return turnNumber;
	}

	public String player1Name() {
		return this.tfPlayer1Name.getText();
	}

	public String player2Name() {
		return this.tfPlayer2Name.getText();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
