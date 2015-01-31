package org.bakkes.game.controller.init.scripting.dsl;

import org.bakkes.game.controller.init.scripting.dsl.anotation.Required;
import org.bakkes.game.model.IInteractable;
import org.bakkes.game.model.entity.IOverworldEntity;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.util.Log;

/**
 * an overworld entity dsl
 *
 * things that can exist in the overwolrd are usualy scripted with help
 * of this dsl.
 */
public abstract class AOverworldEntityDsl extends AInteractableDsl{
	private boolean isPositionSet = false;

	@Required
	public void location(final Integer left, final Integer top){
		isPositionSet = true;
		getTarget().setPosition(new Tile(left, top).topLeftPixels());
	}

	public void name(final String name){
		Log.info("setting name to " + name);
		getTarget().setName(name);
	}
	@Override
	protected abstract IOverworldEntity getTarget();

	@Override
	public boolean isDone(){
		return super.isDone() && isPositionSet;
	}
	@Override
	protected IInteractable getInteractTarget() {
		return getTarget();
	}

}
