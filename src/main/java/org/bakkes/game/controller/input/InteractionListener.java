package org.bakkes.game.controller.input;

import org.bakkes.game.model.entity.EntityTracker;
import org.bakkes.game.model.entity.IOverworldEntity;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.map.LayerdMap;
import org.lwjgl.input.Keyboard;

import com.google.inject.Inject;

public class InteractionListener implements IKeyListener {
	private @Inject Player player;
	private @Inject EntityTracker<IOverworldEntity> overworldEntitiesTracker;
	private @Inject LayerdMap map;

	@Override
	public void KeyDown(final int key, final char c) {
		if(key == Keyboard.KEY_SPACE) {
			final IOverworldEntity entity = findFacingEntity();
			if(entity == null){
				return;
			}
			entity.interact();
		}
	}

	@Override
	public void KeyUp(final int key, final char c) {
		// TODO Auto-generated method stub

	}

	private IOverworldEntity findFacingEntity(){
		return overworldEntitiesTracker.findEntityByTile(player.getDirectionTile().plus(player.getTile()));
	}

}
