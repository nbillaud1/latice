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
	void test_check_if_tile_can_be_put() {
		GameBoard gameBoard = new GameBoard();
		Referer referer = new Referer();
		Tile tileFeather = new Tile(Shape.FEATHER, Colors.GREEN);
		Tile tileTurtle = new Tile(Shape.TURTLE, Colors.GREEN);
		Tile tileRedGecko = new Tile(Shape.GECKO, Colors.RED);
		Tile tileGreenGecko = new Tile(Shape.GECKO, Colors.GREEN);
		gameBoard.addBoard(1, 1, tileFeather);
		
		gameBoard.addBoard(5, 5, tileTurtle);
		gameBoard.addBoard(4, 4, tileFeather);
		
		gameBoard.addBoard(6, 6, tileFeather);
		gameBoard.addBoard(7, 7, tileGreenGecko);
		gameBoard.addBoard(6, 8, tileTurtle);
		
		
		gameBoard.printGameBoard();
		
		int noNext = referer.checkAround(gameBoard, 4, 4, tileFeather);
		int notCompatibleNext = referer.checkAround(gameBoard, 0, 1, tileRedGecko);
		int twoNext = referer.checkAround(gameBoard, 3, 4, tileTurtle);
		int threeNext = referer.checkAround(gameBoard, 5, 6, tileTurtle);
		int fourNext = referer.checkAround(gameBoard, 1, 1, tileGreenGecko);
		
		assertTrue(noNext == 0);
		assertTrue(notCompatibleNext == -1);
		assertTrue(twoNext == 2);
		//assertTrue(threeNext == 3);
		//assertTrue(fourNext == 4);
	}

}
