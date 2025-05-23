package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class MainPool {

	private ArrayList<Tile> tiles;
	
	public MainPool() {
		this.tiles = new ArrayList<Tile>();
		
		//Feather tiles
		this.tiles.add(new Tile(Shape.FEATHER, Colors.YELLOW));
		this.tiles.add(new Tile(Shape.FEATHER, Colors.YELLOW));
		
		this.tiles.add(new Tile(Shape.FEATHER, Colors.NAVY));
		this.tiles.add(new Tile(Shape.FEATHER, Colors.NAVY));
		
		this.tiles.add(new Tile(Shape.FEATHER, Colors.MAGENTA));
		this.tiles.add(new Tile(Shape.FEATHER, Colors.MAGENTA));
		
		this.tiles.add(new Tile(Shape.FEATHER, Colors.RED));
		this.tiles.add(new Tile(Shape.FEATHER, Colors.RED));
		
		this.tiles.add(new Tile(Shape.FEATHER, Colors.GREEN));
		this.tiles.add(new Tile(Shape.FEATHER, Colors.GREEN));
		
		this.tiles.add(new Tile(Shape.FEATHER, Colors.TEAL));
		this.tiles.add(new Tile(Shape.FEATHER, Colors.TEAL));
		
		
		//Bird tiles
		this.tiles.add(new Tile(Shape.BIRD, Colors.YELLOW));
		this.tiles.add(new Tile(Shape.BIRD, Colors.YELLOW));
		
		this.tiles.add(new Tile(Shape.BIRD, Colors.NAVY));
		this.tiles.add(new Tile(Shape.BIRD, Colors.NAVY));
		
		this.tiles.add(new Tile(Shape.BIRD, Colors.MAGENTA));
		this.tiles.add(new Tile(Shape.BIRD, Colors.MAGENTA));
		
		this.tiles.add(new Tile(Shape.BIRD, Colors.RED));
		this.tiles.add(new Tile(Shape.BIRD, Colors.RED));
		
		this.tiles.add(new Tile(Shape.BIRD, Colors.GREEN));
		this.tiles.add(new Tile(Shape.BIRD, Colors.GREEN));
		
		this.tiles.add(new Tile(Shape.BIRD, Colors.TEAL));
		this.tiles.add(new Tile(Shape.BIRD, Colors.TEAL));
		
		
		//Turtle tiles
		this.tiles.add(new Tile(Shape.TURTLE, Colors.YELLOW));
		this.tiles.add(new Tile(Shape.TURTLE, Colors.YELLOW));
		
		this.tiles.add(new Tile(Shape.TURTLE, Colors.NAVY));
		this.tiles.add(new Tile(Shape.TURTLE, Colors.NAVY));
		
		this.tiles.add(new Tile(Shape.TURTLE, Colors.MAGENTA));
		this.tiles.add(new Tile(Shape.TURTLE, Colors.MAGENTA));
		
		this.tiles.add(new Tile(Shape.TURTLE, Colors.RED));
		this.tiles.add(new Tile(Shape.TURTLE, Colors.RED));
		
		this.tiles.add(new Tile(Shape.TURTLE, Colors.GREEN));
		this.tiles.add(new Tile(Shape.TURTLE, Colors.GREEN));
		
		this.tiles.add(new Tile(Shape.TURTLE, Colors.TEAL));
		this.tiles.add(new Tile(Shape.TURTLE, Colors.TEAL));
		
		
		//Flower tiles
		this.tiles.add(new Tile(Shape.FLOWER, Colors.YELLOW));
		this.tiles.add(new Tile(Shape.FLOWER, Colors.YELLOW));
		
		this.tiles.add(new Tile(Shape.FLOWER, Colors.NAVY));
		this.tiles.add(new Tile(Shape.FLOWER, Colors.NAVY));
		
		this.tiles.add(new Tile(Shape.FLOWER, Colors.MAGENTA));
		this.tiles.add(new Tile(Shape.FLOWER, Colors.MAGENTA));
		
		this.tiles.add(new Tile(Shape.FLOWER, Colors.RED));
		this.tiles.add(new Tile(Shape.FLOWER, Colors.RED));
		
		this.tiles.add(new Tile(Shape.FLOWER, Colors.GREEN));
		this.tiles.add(new Tile(Shape.FLOWER, Colors.GREEN));
		
		this.tiles.add(new Tile(Shape.FLOWER, Colors.TEAL));
		this.tiles.add(new Tile(Shape.FLOWER, Colors.TEAL));
		
		//Gecko tiles
		this.tiles.add(new Tile(Shape.GECKO, Colors.YELLOW));
		this.tiles.add(new Tile(Shape.GECKO, Colors.YELLOW));
		
		this.tiles.add(new Tile(Shape.GECKO, Colors.NAVY));
		this.tiles.add(new Tile(Shape.GECKO, Colors.NAVY));
		
		this.tiles.add(new Tile(Shape.GECKO, Colors.MAGENTA));
		this.tiles.add(new Tile(Shape.GECKO, Colors.MAGENTA));
		
		this.tiles.add(new Tile(Shape.GECKO, Colors.RED));
		this.tiles.add(new Tile(Shape.GECKO, Colors.RED));
		
		this.tiles.add(new Tile(Shape.GECKO, Colors.GREEN));
		this.tiles.add(new Tile(Shape.GECKO, Colors.GREEN));
		
		this.tiles.add(new Tile(Shape.GECKO, Colors.TEAL));
		this.tiles.add(new Tile(Shape.GECKO, Colors.TEAL));
		
		
		//Dolphin tiles
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.YELLOW));
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.YELLOW));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.NAVY));
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.NAVY));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.MAGENTA));
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.MAGENTA));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.RED));
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.RED));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.GREEN));
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.GREEN));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.TEAL));
		this.tiles.add(new Tile(Shape.DOLPHIN, Colors.TEAL));
		
		
	}

	public ArrayList<Tile> tiles() {
		return this.tiles;
	}
	
	public ArrayList<ArrayList<Tile>> deal() {
		ArrayList<Tile> tilesPoolPlayer1 = new ArrayList<Tile>();
		ArrayList<Tile> tilesPoolPlayer2 = new ArrayList<Tile>();	
		//turn détermine quelle pool on va remplir
		int turn = 0;
		for(Tile tile : this.tiles()) {
			if (turn % 2 == 0) {
				tilesPoolPlayer1.add(tile);
			}
			else{
				tilesPoolPlayer2.add(tile);
			}
			turn++;
		}
		ArrayList<ArrayList<Tile>> pools = new ArrayList<>();
		pools.add(tilesPoolPlayer1);
		pools.add(tilesPoolPlayer2);
		return pools;
	}
	
	public void shuffle() {
		Collections.shuffle(this.tiles());
	}
	
	
}
