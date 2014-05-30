package org.bakkes.game.entity;

import org.bakkes.game.entity.follower.IFollower;
import org.bakkes.game.math.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Entity {
	protected Vector2 position;
	protected IFollower follower;
	
	public void init(GameContainer gc) {}
	public abstract void update(GameContainer gc, int delta);
	public abstract void render(GameContainer gc, Graphics g);
	
	public Vector2 getPosition() {
		return position;
	}
}
