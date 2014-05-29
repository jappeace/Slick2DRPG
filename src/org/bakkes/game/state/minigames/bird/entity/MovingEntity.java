package org.bakkes.game.state.minigames.bird.entity;

import org.bakkes.game.math.Vector2;

public abstract class MovingEntity extends BaseGameEntity {
	public Vector2 velocity;
	public Vector2 heading;
	public Vector2 side;
	protected float mass;
	public float maxSpeed;
	protected float maxForce;
	protected float maxTurnRate;
	
	public MovingEntity(Vector2 position, float radius, Vector2 velocity, float maxSpeed, Vector2 heading, float mass, Vector2 scale, float turnRate, float maxForce) {
		super();
		this.position = position;
		this.boundingRadius = radius;
		this.velocity = velocity;
		this.maxSpeed = maxSpeed;
		this.heading = heading;
		this.side = heading.perp();
		this.mass = mass;
		this.scale = scale;
		this.maxTurnRate = turnRate;
		this.maxForce = maxForce;
	}
	
	public void HeadTo(Vector2 heading) {
		this.heading = heading;
		this.side = heading.perp();
	}
}
