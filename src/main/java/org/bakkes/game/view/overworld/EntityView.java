package org.bakkes.game.view.overworld;

import org.bakkes.game.R;
import org.bakkes.game.controller.scripting.ScriptLoader;
import org.bakkes.game.controller.scripting.dsl.AnimationDsl;
import org.bakkes.game.model.entity.Entity;
import org.bakkes.game.view.AView;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;

public class EntityView extends AView{

	private Animation[] _animation;
	private Entity entity;
	private @Inject ScriptLoader loader;
	private @Inject AnimationDsl dsl;
	private static final String DEFAULT_ANIMATION = "default";

	@Inject
	public EntityView(final AnimationDsl dsl, final ScriptLoader loader){
	}
	@Override
	public void renderView(final Graphics g) {
		final Animation animation = _animation[getEntity().getFacing()];
		if(getEntity().isWalking()){
			animation.setAutoUpdate(true);
		}else{
			animation.setAutoUpdate(false);
			animation.setCurrentFrame(0);
		}
		animation.draw(getEntity().getPosition().getX(), getEntity().getPosition().getY());
	}
	private Entity getEntity() {
		return entity;
	}
	public void setEntity(final Entity entity) {
		this.entity = entity;
		// try loading a entity representation based on its name
		// I considered giving a animation name but that is just tedious
		// Don't forget to replace spaces, filesystems hate spaces
		if(!loader.load(R.overworldAnimationScript + entity.getName().toLowerCase().replace(' ', '_')  + ".dsl", dsl)){
			loader.load(R.overworldAnimationScript + DEFAULT_ANIMATION + ".dsl", dsl);
		}
        _animation = dsl.getResult();
	}

}
