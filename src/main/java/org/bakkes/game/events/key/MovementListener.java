package org.bakkes.game.events.key;

import org.bakkes.game.model.entity.Player;
import org.bakkes.game.model.map.Tile;
import org.lwjgl.input.Keyboard;

import com.google.inject.Inject;
import com.google.inject.Provider;

//Class to use WASD for movement
public class MovementListener implements IKeyListener {

	private @Inject Player player;
	private @Inject Provider<Tile> tileProvider;

	@Override
	public void KeyDown(final int key, final char c) {
		final Tile diff = tileProvider.get();
		if(key == Keyboard.KEY_W) {
			diff.top --;
		} else if(key == Keyboard.KEY_A) {
			diff.left --;
		} else if(key == Keyboard.KEY_S) {
			diff.top ++;
		} else if(key == Keyboard.KEY_D) {
			diff.left++;
		}
		player.moveTo(player.getTile().plus(diff));
	}

	@Override
	public void KeyUp(final int key, final char c) {
		// TODO Auto-generated method stub

	}

}
