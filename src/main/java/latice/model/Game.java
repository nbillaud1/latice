package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	private MainPool mainPool;
	
	public Game() {
		this.mainPool = new MainPool();
	}
	
	public MainPool mainPool() {
		return this.mainPool;
	}
}
