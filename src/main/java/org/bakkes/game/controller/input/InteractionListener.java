package org.bakkes.game.controller.input;

import org.bakkes.game.model.entity.EntityTracker;
import org.bakkes.game.model.entity.IOverworldEntity;
import org.bakkes.game.model.entity.player.Player;

import com.google.inject.Inject;

public class InteractionListener extends AKeyListener {
	private @Inject Player player;
	private @Inject EntityTracker<IOverworldEntity> overworldEntitiesTracker;

	@Override
	public void KeyDown(final Key key) {
		if(!key.isConfirm()) {
			return;
        }
        final IOverworldEntity entity = findFacingEntity();
        if(entity == null){
            return;
        }
        /*
         * this is where the fun starts...
         * some functions in interact require userinput, the scripting has to wait
         * but this can't happen on this thread because this is the render thread...
         * So fun
         */
        new Thread(new Runnable(){
            @Override
            public void run() {
                entity.interact();
            }
        }).start();
    }

	private IOverworldEntity findFacingEntity(){
		return overworldEntitiesTracker.findEntityByTile(player.getDirectionTile().plus(player.getTile()));
	}

}
