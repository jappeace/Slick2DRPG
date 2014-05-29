package org.bakkes.game.state.minigames.bird.entity;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.SteeringBehavior;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Bird extends MovingEntity {

	private SteeringBehavior behavior;
	
	public Bird(Vector2 position, float radius, Vector2 velocity,
			float maxSpeed, Vector2 heading, float mass, Vector2 scale,
			float turnRate, float maxForce) {
		super(position, radius, velocity, maxSpeed, heading, mass, scale, turnRate,
				maxForce);
		behavior = new SteeringBehavior();
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		Vector2 oldPos = position.copy();
		Vector2 steeringForce = behavior.calculate();
		Vector2 acceleration = steeringForce.divideBy(mass);
		velocity.add(acceleration.multiply(delta));
		velocity.truncate(maxSpeed);
		position.add(velocity.multiply((float) delta));
		this.heading = velocity.normalizeCopy();
		this.side = this.heading.perp();
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.white);
		g.drawRect(position.getX(), position.getY(), 5, 5);
	}

}
