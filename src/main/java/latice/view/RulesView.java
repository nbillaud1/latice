package latice.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

//Règles du jeu en sections illustrées : icône, texte, et exemples avec les vraies tuiles
public final class RulesView {
	private static final double CELL = 40;

	private RulesView() {
	}

	public static VBox build() {
		VBox box = new VBox(16,
				section(Icons.TROPHY, "But du jeu",
						"Associez les tuiles par couleur ou par forme. Chaque joueur commence avec une pioche de 31 tuiles "
						+ "et une main de 5. Le gagnant est celui qui se débarrasse de toutes ses tuiles, ou qui en a "
						+ "le moins à la fin des tours impartis.",
						goalExample()),
				section(Icons.PLACE, "Poser une tuile",
						"La première tuile se pose sur la lune, au centre. Ensuite, une tuile doit toucher par un côté "
						+ "une tuile déjà posée, et avoir la même forme ou la même couleur que chacune de ses voisines "
						+ "(les diagonales ne comptent pas).",
						placeExample()),
				section(Icons.BOLT, "Votre tour",
						"Vous avez une action par tour. Choisissez :",
						actions()),
				section(Icons.SUN, "Les pierres",
						"Plus votre tuile a de voisines, plus elle rapporte. Une action supplémentaire coûte une pierre soleil "
						+ "ou deux demi-pierres. Au-delà de 3 pierres soleil en fin de tour, l'excédent est perdu.",
						stones()));
		box.getStyleClass().add("rules-content");
		return box;
	}

	private static VBox section(String icon, String title, String text, Node example) {
		Label heading = new Label(title, Icons.icon(icon, 26, "rules-icon"));
		heading.getStyleClass().add("rules-heading");
		heading.setGraphicTextGap(10);
		VBox card = new VBox(10, heading, flow(text), example);
		card.getStyleClass().add("rules-card");
		return card;
	}

	private static TextFlow flow(String text) {
		TextFlow flow = new TextFlow();
		StoneText.fill(flow, text, "rules-text");
		return flow;
	}

	private static ImageView img(String name, double size) {
		ImageView view = new ImageView("/latice/image/" + name + ".png");
		view.setFitWidth(size);
		view.setFitHeight(size);
		view.setSmooth(true);
		return view;
	}

	//Les 6 formes, puis les 6 couleurs
	private static Node goalExample() {
		HBox shapes = new HBox(6);
		for (String shape : new String[] {"bird", "dolphin", "feather", "flower", "gecko", "turtle"}) {
			shapes.getChildren().add(img(shape + "_n", 44));
		}
		HBox colors = new HBox(6);
		for (String color : new String[] {"g", "m", "n", "r", "t", "y"}) {
			colors.getChildren().add(img("turtle_" + color, 44));
		}
		HBox row = new HBox(24, labelled(shapes, "6 formes"), labelled(colors, "6 couleurs"));
		row.setAlignment(Pos.CENTER);
		return row;
	}

	private static Node labelled(Node node, String caption) {
		Label label = new Label(caption);
		label.getStyleClass().add("rules-caption");
		VBox box = new VBox(4, node, label);
		box.setAlignment(Pos.CENTER);
		return box;
	}

	//Petit morceau de plateau : null = case vide, "!" devant le nom = tuile que l'on pose
	private static Node board(String[][] cells) {
		GridPane grid = new GridPane();
		grid.getStyleClass().add("mini-board");
		grid.setMaxSize(GridPane.USE_PREF_SIZE, GridPane.USE_PREF_SIZE); //le cadre en bois ne s'étire pas
		for (int r = 0; r < cells.length; r++) {
			for (int c = 0; c < cells[r].length; c++) {
				StackPane cell = new StackPane();
				cell.getStyleClass().add("mini-cell");
				cell.setPrefSize(CELL, CELL);
				String name = cells[r][c];
				if (name != null) {
					boolean placed = name.startsWith("!");
					cell.getChildren().add(img(placed ? name.substring(1) : name, CELL - 4));
					if (placed) {
						cell.getStyleClass().add("mini-placed");
					}
				}
				grid.add(cell, c, r);
			}
		}
		return grid;
	}

	private static Node verdict(Node board, boolean ok, String text) {
		Label label = new Label(text, Icons.icon(ok ? Icons.CHECK : Icons.CLOSE, 18, ok ? "ok-icon" : "ko-icon"));
		label.getStyleClass().addAll("rules-caption", ok ? "ok" : "ko");
		label.setWrapText(true);
		label.setMaxWidth(150);
		VBox box = new VBox(8, board, label);
		box.setAlignment(Pos.TOP_CENTER);
		return box;
	}

	private static Node placeExample() {
		HBox row = new HBox(22,
				verdict(board(new String[][] {{"dolphin_r", "!dolphin_g"}}), true, "Même forme"),
				verdict(board(new String[][] {{"dolphin_r", "!turtle_r"}}), true, "Même couleur"),
				verdict(board(new String[][] {{"dolphin_r", "!turtle_g"}}), false, "Ni l'une ni l'autre"),
				verdict(board(new String[][] {{"gecko_y", null}, {"!gecko_n", "dolphin_r"}}), false,
						"Doit aller avec chaque voisine"));
		row.setAlignment(Pos.CENTER);
		return row;
	}

	private static Node action(String icon, String title, String text) {
		Label name = new Label(title, Icons.icon(icon, 18));
		name.getStyleClass().add("rules-action");
		Label detail = new Label(text);
		detail.getStyleClass().add("rules-text");
		detail.setWrapText(true);
		HBox.setHgrow(detail, Priority.ALWAYS);
		name.setMinWidth(220);
		HBox row = new HBox(14, name, detail);
		row.setAlignment(Pos.CENTER_LEFT);
		return row;
	}

	private static Node actions() {
		return new VBox(8,
				action(Icons.PLACE, "Poser une tuile", "glissez-la de votre main vers le plateau."),
				action(Icons.SWAP, "Changer de main", "remplace vos 5 tuiles, et termine votre tour."),
				action(Icons.SKIP, "Finir le tour", "passe la main à l'adversaire."),
				action(Icons.ADD, "Acheter une action", "après avoir posé, pour rejouer avec vos pierres."));
	}

	private static Node gain(Node board, String text) {
		TextFlow caption = flow(text);
		caption.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		VBox box = new VBox(8, board, caption);
		box.setAlignment(Pos.TOP_CENTER);
		box.setMaxWidth(150);
		return box;
	}

	private static Node stones() {
		String n = "flower_n";
		HBox row = new HBox(18,
				gain(board(new String[][] {{n, "!flower_r", "flower_y"}}), "2 voisines : Double, une demi-pierre"),
				gain(board(new String[][] {{null, "flower_g", null}, {n, "!flower_r", "flower_y"}}),
						"3 voisines : Trèfle, une pierre soleil"),
				gain(board(new String[][] {{null, "flower_g", null}, {n, "!flower_r", "flower_y"}, {null, "flower_m", null}}),
						"4 voisines : Latice, deux pierres soleil"),
				gain(board(new String[][] {{"sun"}}), "Poser sur un soleil : une pierre soleil"));
		row.setAlignment(Pos.TOP_CENTER);
		return row;
	}
}
