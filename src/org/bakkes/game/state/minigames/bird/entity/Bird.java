package org.bakkes.game.state.minigames.bird.entity;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.SteeringBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.ArriveBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.FleeBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.SeekBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.WanderBehavior;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Bird extends MovingEntity {
	private static final float MOVEMENT_PER_MS = 0.1f; //move max 0.1f per MS
	
	private SteeringBehavior behavior;
	
	public Bird(Vector2 position, float radius, Vector2 velocity,
			float maxSpeed, Vector2 heading, float mass, Vector2 scale,
			float turnRate, float maxForce) {
		super(position, radius, velocity, maxSpeed, heading, mass, scale, turnRate,
				maxForce);
		behavior = new SteeringBehavior(this);
		behavior.addBehavior(new WanderBehavior(this));
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		Vector2 oldPos = position.copy();
		Vector2 steeringForce = behavior.calculate();
		Vector2 acceleration = steeringForce.divideBy(mass);
		velocity.add(acceleration.multiply(delta * MOVEMENT_PER_MS));
		velocity.truncate(maxSpeed);
		position.add(velocity.multiply(delta * MOVEMENT_PER_MS));
		this.heading = velocity.normalizeCopy();
		this.side = this.heading.perp();
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.white);
		g.drawRect(position.getX(), position.getY(), 5, 5);
	}

}
