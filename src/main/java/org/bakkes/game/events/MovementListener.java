package org.bakkes.game.events;

import org.bakkes.game.map.Tile;
import org.bakkes.game.state.PlayingGameState;
import org.lwjgl.input.Keyboard;

//Class to use WASD for movement
public class MovementListener implements GameKeyListener {

	private PlayingGameState game;

	public MovementListener(final PlayingGameState playingGameState) {
		this.game = playingGameState;
	}

	@Override
	public void KeyDown(final int key, final char c) {
		final Tile diff = new Tile(0, 0);
		if(key == Keyboard.KEY_W) { //W
			diff.top --;
		} else if(key == 30) { //a
			diff.left --;
		} else if(key == 31) { //s
			diff.top ++;
		} else if(key == 32) { //d
			diff.left++;
		}
		game.getPlayer().moveTo(game.getPlayer().getTile().plus(diff));
	}

	@Override
	public void KeyUp(final int key, final char c) {
		// TODO Auto-generated method stub

	}

}
