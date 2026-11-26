package latice.view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

//Texte où chaque mention d'une pierre (soleil ou demi) est précédée de son icône
public final class StoneText {
	private static final Pattern STONE = Pattern.compile("(?i)demi[- ]pierres?|pierres? soleil");

	private StoneText() {
	}

	//Découpe le message : chaque mention de pierre devient un morceau à part
	public static List<String> segments(String message) {
		List<String> parts = new ArrayList<>();
		Matcher m = STONE.matcher(message);
		int last = 0;
		while (m.find()) {
			if (m.start() > last) {
				parts.add(message.substring(last, m.start()));
			}
			parts.add(m.group());
			last = m.end();
		}
		if (last < message.length()) {
			parts.add(message.substring(last));
		}
		return parts;
	}

	public static boolean isStone(String segment) {
		return STONE.matcher(segment).matches();
	}

	public static boolean isHalfStone(String segment) {
		return isStone(segment) && segment.toLowerCase().startsWith("demi");
	}

	//Icône de pierre colorée (soleil jaune, demi violette)
	public static Node icon(boolean half, double size) {
		return Icons.icon(half ? Icons.HALF_STONE : Icons.SUN, size, half ? "half-icon" : "sun-icon");
	}

	//Remplit un TextFlow avec le message, icônes comprises
	public static void fill(TextFlow flow, String message, String textStyleClass) {
		flow.getChildren().clear();
		for (String segment : segments(message)) {
			if (isStone(segment)) {
				Node icon = icon(isHalfStone(segment), 18);
				icon.setTranslateY(8); //aligne l'icône sur le milieu des lettres
				flow.getChildren().add(icon);
				segment = " " + segment;
			}
			Text text = new Text(segment);
			text.getStyleClass().add(textStyleClass);
			flow.getChildren().add(text);
		}
	}
}
