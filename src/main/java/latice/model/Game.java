package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	//private GameBoard gameBoard;
	//private ArrayList<Player> players;
	private MainPool mainPool;
	
	public Game() {
		this.mainPool = new MainPool();
	}
	
	public MainPool mainPool() {
		return this.mainPool;
	}
	
	/*public Player whoStarts() {
		//TODO
	}*/
}
