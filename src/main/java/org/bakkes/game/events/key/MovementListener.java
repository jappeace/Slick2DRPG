package org.bakkes.game.events.key;

import org.bakkes.game.model.map.Tile;
import org.bakkes.game.state.OverworldState;
import org.lwjgl.input.Keyboard;

//Class to use WASD for movement
public class MovementListener implements IKeyListener {

	private OverworldState game;

	public MovementListener(final OverworldState playingGameState) {
		this.game = playingGameState;
	}

	@Override
	public void KeyDown(final int key, final char c) {
		final Tile diff = new Tile(0, 0);
		if(key == Keyboard.KEY_W) {
			diff.top --;
		} else if(key == Keyboard.KEY_A) {
			diff.left --;
		} else if(key == Keyboard.KEY_S) {
			diff.top ++;
		} else if(key == Keyboard.KEY_D) {
			diff.left++;
		}
		game.getPlayer().moveTo(game.getPlayer().getTile().plus(diff));
	}

	@Override
	public void KeyUp(final int key, final char c) {
		// TODO Auto-generated method stub

	}

}
