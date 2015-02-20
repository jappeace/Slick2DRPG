package org.bakkes.game.controller.state.overworld.event.key;

import com.google.inject.Inject;
import org.bakkes.game.controller.async.IThreadPool;
import org.bakkes.game.controller.state.event.input.AKeyListener;
import org.bakkes.game.controller.state.event.input.Key;
import org.bakkes.game.model.entity.EntityTracker;
import org.bakkes.game.model.entity.IOverworldEntity;
import org.bakkes.game.model.entity.player.Player;

public class InteractionListener extends AKeyListener {
	private @Inject Player player;
	private @Inject EntityTracker<IOverworldEntity> overworldEntitiesTracker;
	private @Inject IThreadPool pool;
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
         *
         * not that you really don't need to keep a ref to the thread once its spinning it will do its thing
         * gets garbage collected automaticly, the util functions of a thread are bullshit anyways
         */
        pool.execute(new Runnable(){
            @Override
            public void run() {
                entity.interact();
            }
        });
    }

	private IOverworldEntity findFacingEntity(){
		return overworldEntitiesTracker.findEntityByTile(player.getDirectionTile().plus(player.getTile()));
	}

}
