package org.bakkes.game.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	protected Vector2f position;

	public void init(final GameContainer gc) {}
	public abstract void update(GameContainer gc, int delta);
	public abstract void render(GameContainer gc, Graphics g);

	public Vector2f getPosition() {
		return position;
	}
}
