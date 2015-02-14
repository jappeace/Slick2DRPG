package org.bakkes.game.controller.init.scripting.dsl;

import org.bakkes.game.model.IHasSpriteName;

/**
 * extend this class if the current bean you're modifying has some weird sprite naming issues (like numbers)
 * and you are to lazy to rename all the sprites
 */
public abstract class ASpriteNamedDsl extends ADsl {

	public void setSpriteName(final String to){
		getTarget().setSpriteName(to);
	}

	protected abstract IHasSpriteName getTarget();
}
