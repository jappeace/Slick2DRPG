package org.bakkes.game.events;

import org.bakkes.game.Game;
import org.bakkes.game.PlayingGameState;
import org.bakkes.game.math.Vector2;

//Class to use WASD for movement
public class MovementListener implements GameKeyListener {

	private PlayingGameState game;
	
	public MovementListener(PlayingGameState playingGameState) {
		this.game = playingGameState;
	}
	
	public void KeyDown(int key, char c) {
		Vector2 diff = new Vector2(0, 0);
		if(key == 17) { //W
			diff.addY(-1);
		} else if(key == 30) { //a
			diff.addX(-1);
		} else if(key == 31) { //s
			diff.addY(1);
		} else if(key == 32) { //d
			diff.addX(1);
		}
		Vector2 newPos = game.getPlayer().getGridPosition().copy();
		newPos.add(diff);
		game.getPlayer().moveTo(newPos);
	}

	public void KeyUp(int key, char c) {
		// TODO Auto-generated method stub
		
	}

}
