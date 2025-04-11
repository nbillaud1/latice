package latice.model;

import java.util.ArrayList;

public class MainPool {

	private ArrayList<Tile> tiles;
	
	public MainPool() {
		this.tiles = new ArrayList<Tile>();
		
		//Feather tiles
		this.tiles.add(new Tile(Shape.FEATHER, Color.YELLOW));
		this.tiles.add(new Tile(Shape.FEATHER, Color.YELLOW));
		
		this.tiles.add(new Tile(Shape.FEATHER, Color.NAVY));
		this.tiles.add(new Tile(Shape.FEATHER, Color.NAVY));
		
		this.tiles.add(new Tile(Shape.FEATHER, Color.MAGENTA));
		this.tiles.add(new Tile(Shape.FEATHER, Color.MAGENTA));
		
		this.tiles.add(new Tile(Shape.FEATHER, Color.RED));
		this.tiles.add(new Tile(Shape.FEATHER, Color.RED));
		
		this.tiles.add(new Tile(Shape.FEATHER, Color.GREEN));
		this.tiles.add(new Tile(Shape.FEATHER, Color.GREEN));
		
		this.tiles.add(new Tile(Shape.FEATHER, Color.TEAL));
		this.tiles.add(new Tile(Shape.FEATHER, Color.TEAL));
		
		
		//Bird tiles
		this.tiles.add(new Tile(Shape.BIRD, Color.YELLOW));
		this.tiles.add(new Tile(Shape.BIRD, Color.YELLOW));
		
		this.tiles.add(new Tile(Shape.BIRD, Color.NAVY));
		this.tiles.add(new Tile(Shape.BIRD, Color.NAVY));
		
		this.tiles.add(new Tile(Shape.BIRD, Color.MAGENTA));
		this.tiles.add(new Tile(Shape.BIRD, Color.MAGENTA));
		
		this.tiles.add(new Tile(Shape.BIRD, Color.RED));
		this.tiles.add(new Tile(Shape.BIRD, Color.RED));
		
		this.tiles.add(new Tile(Shape.BIRD, Color.GREEN));
		this.tiles.add(new Tile(Shape.BIRD, Color.GREEN));
		
		this.tiles.add(new Tile(Shape.BIRD, Color.TEAL));
		this.tiles.add(new Tile(Shape.BIRD, Color.TEAL));
		
		
		//Turtle tiles
		this.tiles.add(new Tile(Shape.TURTLE, Color.YELLOW));
		this.tiles.add(new Tile(Shape.TURTLE, Color.YELLOW));
		
		this.tiles.add(new Tile(Shape.TURTLE, Color.NAVY));
		this.tiles.add(new Tile(Shape.TURTLE, Color.NAVY));
		
		this.tiles.add(new Tile(Shape.TURTLE, Color.MAGENTA));
		this.tiles.add(new Tile(Shape.TURTLE, Color.MAGENTA));
		
		this.tiles.add(new Tile(Shape.TURTLE, Color.RED));
		this.tiles.add(new Tile(Shape.TURTLE, Color.RED));
		
		this.tiles.add(new Tile(Shape.TURTLE, Color.GREEN));
		this.tiles.add(new Tile(Shape.TURTLE, Color.GREEN));
		
		this.tiles.add(new Tile(Shape.TURTLE, Color.TEAL));
		this.tiles.add(new Tile(Shape.TURTLE, Color.TEAL));
		
		
		//Flower tiles
		this.tiles.add(new Tile(Shape.FLOWER, Color.YELLOW));
		this.tiles.add(new Tile(Shape.FLOWER, Color.YELLOW));
		
		this.tiles.add(new Tile(Shape.FLOWER, Color.NAVY));
		this.tiles.add(new Tile(Shape.FLOWER, Color.NAVY));
		
		this.tiles.add(new Tile(Shape.FLOWER, Color.MAGENTA));
		this.tiles.add(new Tile(Shape.FLOWER, Color.MAGENTA));
		
		this.tiles.add(new Tile(Shape.FLOWER, Color.RED));
		this.tiles.add(new Tile(Shape.FLOWER, Color.RED));
		
		this.tiles.add(new Tile(Shape.FLOWER, Color.GREEN));
		this.tiles.add(new Tile(Shape.FLOWER, Color.GREEN));
		
		this.tiles.add(new Tile(Shape.FLOWER, Color.TEAL));
		this.tiles.add(new Tile(Shape.FLOWER, Color.TEAL));
		
		//Gecko tiles
		this.tiles.add(new Tile(Shape.GECKO, Color.YELLOW));
		this.tiles.add(new Tile(Shape.GECKO, Color.YELLOW));
		
		this.tiles.add(new Tile(Shape.GECKO, Color.NAVY));
		this.tiles.add(new Tile(Shape.GECKO, Color.NAVY));
		
		this.tiles.add(new Tile(Shape.GECKO, Color.MAGENTA));
		this.tiles.add(new Tile(Shape.GECKO, Color.MAGENTA));
		
		this.tiles.add(new Tile(Shape.GECKO, Color.RED));
		this.tiles.add(new Tile(Shape.GECKO, Color.RED));
		
		this.tiles.add(new Tile(Shape.GECKO, Color.GREEN));
		this.tiles.add(new Tile(Shape.GECKO, Color.GREEN));
		
		this.tiles.add(new Tile(Shape.GECKO, Color.TEAL));
		this.tiles.add(new Tile(Shape.GECKO, Color.TEAL));
		
		
		//Dolphin tiles
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.YELLOW));
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.YELLOW));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.NAVY));
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.NAVY));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.MAGENTA));
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.MAGENTA));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.RED));
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.RED));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.GREEN));
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.GREEN));
		
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.TEAL));
		this.tiles.add(new Tile(Shape.DOLPHIN, Color.TEAL));
		
		
	}

	public ArrayList<Tile> tiles() {
		return this.tiles;
	}
	
	
}
