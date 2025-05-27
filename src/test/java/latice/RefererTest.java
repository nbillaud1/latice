package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.controller.Referer;
import latice.model.Colors;
import latice.model.GameBoard;
import latice.model.Shape;
import latice.model.Tile;

class RefererTest {

	@BeforeEach
	void init() {
		GameBoard gameBoard = new GameBoard();
		Referer referer = new Referer();
	}
	
	@Test
	void test_has_to_play_the_first_tile_on_the_moon() {
		GameBoard gameBoard = new GameBoard();
		Referer referer = new Referer();
		
		Boolean hasToPlayOnTheMoon = referer.firstTileNotPutOnTheMoon(gameBoard.board());
		
		assertTrue(hasToPlayOnTheMoon);
	}
	
	@Test
	void test_grid_already_filled() {
		GameBoard gameBoard = new GameBoard();
		Referer referer = new Referer();
		Tile tileTurtle = new Tile(Shape.TURTLE, Colors.GREEN);
		gameBoard.addBoard(1, 1, tileTurtle);
		
		Boolean firstPos = referer.gridAlreadyFilled(gameBoard.board(), 4, 4);
		Boolean secondPos = referer.gridAlreadyFilled(gameBoard.board(), 0, 0);
		
		assertFalse(firstPos);
		assertTrue(secondPos);
	}
	
	@Test
	void test_is_sun_tile() {
		GameBoard gameBoard = new GameBoard();
		Referer referer = new Referer();
		Tile tileFeather = new Tile(Shape.FEATHER, Colors.GREEN);
		gameBoard.addBoard(1, 1, tileFeather);
		
		Boolean firstSun = referer.isSunTile(gameBoard.board(), 0, 0);
		Boolean lastSun = referer.isSunTile(gameBoard.board(), 8, 8);
		
		assertFalse(firstSun);
		assertTrue(lastSun);
	}
	
	@Test
	void test_check_if_tile_cannot_be_put() {
		GameBoard gameBoard = new GameBoard();
		Referer referer = new Referer();
		Tile tileFeather = new Tile(Shape.FEATHER, Colors.GREEN);
		Tile tileGecko = new Tile(Shape.GECKO, Colors.RED);
		
		gameBoard.addBoard(1, 1, tileFeather);
		int noTileNext = referer.checkAround(gameBoard, 4, 4, tileFeather);
		int notCompatibleNext = referer.checkAround(gameBoard, 0, 1, tileGecko);
		
		assertEquals(noTileNext, 0);
		assertEquals(notCompatibleNext, -1);
		
	}
	
	@Test
	void test_double() {
		GameBoard gameBoard = new GameBoard();
		Referer referer = new Referer();
		Tile tileFeather = new Tile(Shape.FEATHER, Colors.GREEN);
		Tile tileTurtle = new Tile(Shape.TURTLE, Colors.GREEN);

		gameBoard.addBoard(5, 5, tileTurtle);
		gameBoard.addBoard(4, 4, tileFeather);
		int twoNext = referer.checkAround(gameBoard, 4, 3, tileTurtle);
		
		assertEquals(twoNext, 2);
	}
	
	@Test
	void test_trefoil() {
		GameBoard gameBoard = new GameBoard();
		Referer referer = new Referer();
		Tile tileFeather = new Tile(Shape.FEATHER, Colors.GREEN);
		Tile tileTurtle = new Tile(Shape.TURTLE, Colors.GREEN);
		Tile tileGecko = new Tile(Shape.GECKO, Colors.GREEN);
		Tile tileBird = new Tile(Shape.BIRD, Colors.GREEN);
		
		gameBoard.addBoard(5, 5, tileTurtle);
		gameBoard.addBoard(4, 4, tileFeather);
		gameBoard.addBoard(4, 6, tileGecko);
		int threeNext = referer.checkAround(gameBoard, 4, 3, tileBird);
		
		assertEquals(threeNext, 3);
	}
	
	@Test
	void test_latice() {
		GameBoard gameBoard = new GameBoard();
		Referer referer = new Referer();
		Tile tileFeather = new Tile(Shape.FEATHER, Colors.GREEN);
		Tile tileTurtle = new Tile(Shape.TURTLE, Colors.GREEN);
		Tile tileGecko = new Tile(Shape.GECKO, Colors.GREEN);
		Tile tileBird = new Tile(Shape.BIRD, Colors.GREEN);
		Tile tileFlower = new Tile(Shape.FLOWER, Colors.GREEN);
		
		gameBoard.addBoard(5, 5, tileTurtle);
		gameBoard.addBoard(4, 4, tileFeather);
		gameBoard.addBoard(4, 6, tileGecko);
		gameBoard.addBoard(3, 5, tileBird);
		int fourNext = referer.checkAround(gameBoard, 4, 3, tileFlower);
		
		assertEquals(fourNext, 4);
	}

}
