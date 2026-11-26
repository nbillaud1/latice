package latice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import latice.view.StoneText;

class StoneTextTest {

	@Test
	void isole_les_mentions_de_pierres() {
		assertEquals(Arrays.asList("Il faut au moins 2 ", "demi pierres", " ou une ", "pierre soleil", "."),
				StoneText.segments("Il faut au moins 2 demi pierres ou une pierre soleil."));
	}

	@Test
	void message_sans_pierre_reste_entier() {
		assertEquals(Arrays.asList("Vous ne pouvez pas poser une tuile ici"),
				StoneText.segments("Vous ne pouvez pas poser une tuile ici"));
	}

	@Test
	void reconnait_le_type_de_pierre() {
		assertTrue(StoneText.isHalfStone("demi-pierres"));
		assertTrue(StoneText.isStone("Pierres soleil"));
		assertFalse(StoneText.isHalfStone("pierre soleil"));
		assertFalse(StoneText.isStone("pierre"));
	}
}
