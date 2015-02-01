package org.bakkes.game.view.components;

import org.bakkes.game.model.ImageCache;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

public class Sprite extends AShape{

	private Image image;
	private @Inject ImageCache cache;

	public void setSpritePath(final String path){
		try {
			image = cache.load(path);
		} catch (final SlickException e) {
			Log.warn("failed loading sprite " + path);
		}
	}

	@Override
	public float width() {
		return image.getWidth();
	}

	@Override
	public float height() {
		return image.getHeight();
	}

	@Override
	public void render(final Graphics g) {
		g.drawImage(image, x(), y());
	}
}
