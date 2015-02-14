package org.bakkes.game.controller.event.input;

import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.map.Tile;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * ## MovementListener
 * allows the main character to be controlled by the keyboard
 */
public class MovementListener extends AKeyListener implements IKeyListener {

	private @Inject Player player;
	private @Inject Provider<Tile> tileProvider;

	@Override
	public void KeyDown(final Key key) {
		final Tile diff = tileProvider.get();
		if(key.isUp()) {
			diff.top --;
		} else if(key.isLeft()) {
			diff.left --;
		} else if(key.isDown()) {
			diff.top ++;
		} else if(key.isRight()) {
			diff.left ++;
		}
		player.moveTo(player.getTile().plus(diff));
	}
}
