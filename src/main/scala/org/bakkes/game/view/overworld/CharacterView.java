package org.bakkes.game.view.overworld;

import java.nio.file.Path;

import org.bakkes.game.controller.init.scripting.dsl.AnimationDsl;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;
import org.bakkes.game.model.entity.Character;
import org.bakkes.game.view.AView;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class CharacterView extends AView{

	private Animation[] _animation;
	private Character entity;
	private @Inject ScriptLoader loader;
	private @Inject AnimationDsl dsl;
	private static final String DEFAULT_ANIMATION = "default";
	private @Inject @Named("animations") Path path;

	@Override
	public void renderView(final Graphics g) {
		final Animation animation = _animation[getEntity().getFacing().ordinal()];
		if(getEntity().isWalking()){
			animation.setAutoUpdate(true);
		}else{
			animation.setAutoUpdate(false);
			animation.setCurrentFrame(0);
		}
		animation.draw(getEntity().getPosition().getX(), getEntity().getPosition().getY());
	}
	private Character getEntity() {
		return entity;
	}
	public void setEntity(final Character entity) {
		this.entity = entity;
		// try loading a entity representation based on its name
		// I considered giving a animation name but that is just tedious
		// Don't forget to replace spaces, filesystems hate spaces
		if(!loader.load(path.resolve(entity.getSpriteName()  + ".dsl"), dsl)){
			loader.load(path.resolve(DEFAULT_ANIMATION + ".dsl"), dsl);
		}
        _animation = dsl.getResult();
	}

}
