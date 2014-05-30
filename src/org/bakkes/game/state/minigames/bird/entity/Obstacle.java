package org.bakkes.game.state.minigames.bird.entity;

import org.bakkes.game.math.Vector2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Obstacle extends BaseGameEntity {

	public Obstacle(Vector2 position, Vector2 scale, float boundingRadius) {
		this.position = position;
		this.scale = scale;
		this.boundingRadius = boundingRadius;
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		g.fillRect(position.getX(), position.getY(), (float)boundingRadius, (float)boundingRadius);
	}

}
