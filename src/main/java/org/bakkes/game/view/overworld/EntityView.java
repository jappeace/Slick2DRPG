package org.bakkes.game.view.overworld;

import org.bakkes.game.model.ImageCache;
import org.bakkes.game.model.entity.IOverworldEntity;
import org.bakkes.game.view.AView;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

public class EntityView extends AView{
	private @Inject ImageCache imageCache;
	private Image img;
	private IOverworldEntity entity;

	@Override
	protected void renderView(final Graphics g) {
		final Vector2f position = entity.getPosition();
		img.draw(position.x, position.y);
	}

	/**
	 * @param path, the folder containing the sprites
	 * @param entity being rendered
	 * @return itself
	 */
	public EntityView loadView(String path, final IOverworldEntity entity){
		path += "/" + entity.getSpriteName() + ".png";
		try {
			img = imageCache.load( path);
		} catch (final SlickException e) {
			e.printStackTrace();
			Log.warn("entityView loading failed for " + path);
		}
		this.entity = entity;
		return this;
	}
}
