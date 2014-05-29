package org.bakkes.game.state.minigames.bird.entity;

import org.bakkes.game.math.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class BaseGameEntity {
	private static int nextId = 0;
	
	private int id;
	private boolean flag;
	
	protected Vector2 position;
	protected Vector2 scale;
	protected double boundingRadius;
	
	public abstract void update(GameContainer gc, int delta);
	public abstract void render(GameContainer gc, Graphics g);
	
	public BaseGameEntity() {
		this.id = nextId++;
	}
}
