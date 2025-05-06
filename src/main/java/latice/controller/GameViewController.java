package latice.controller;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import latice.model.MainPool;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Tile;

public class GameViewController implements EventHandler<MouseEvent>{
	
	@FXML
	//private Type idObject;
	
	@Override
	public void handle(MouseEvent event) {
		//TODO
    }

    @FXML
    void initialize() {
    	MainPool mainPool = new MainPool();
    	Pool pool = new Pool(); // retourne la MainPool divisée en deux pool.
    	ArrayList<Tile> poolPlayer1 = pool.tiles().get(0);
    	ArrayList<Tile> poolPlayer2 = pool.tiles().get(1);
    	Rack rackPlayer1 = new Rack(poolPlayer1);
    	Rack rackPlayer2 = new Rack(poolPlayer2);
    	System.out.println("Bonjour");
    }
}