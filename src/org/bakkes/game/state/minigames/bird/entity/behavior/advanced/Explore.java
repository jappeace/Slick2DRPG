package org.bakkes.game.state.minigames.bird.entity.behavior.advanced;

import java.util.Random;

import org.bakkes.game.GameInfo;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.SeekBehavior;

public class Explore implements IBehavior {
	private static final float HUNTING_DISTANCE = 30f; //hunt when within 20 pixels
	private static final Random random = new Random(42);
	private MovingEntity entity;
	private SeekBehavior seek;
	private Vector2 velocity;
	
	public Explore(MovingEntity entity) {
		this.entity = entity;
		this.seek = new SeekBehavior(entity);
		this.velocity = new Vector2(0f, 0f);
	}
	
	public Vector2 calculate(Vector2 target) {
		Vector2 dist = target.minusOperator(entity.position);
		//if within hunting distance
		if(Math.abs(dist.getX()) < HUNTING_DISTANCE || Math.abs(dist.getY()) < HUNTING_DISTANCE) {
			return seek.calculate(target);
		}
		velocity.setX(velocity.getX() + random.nextFloat() * 4 - 2);
		velocity.setY(velocity.getY() + random.nextFloat() * 4 - 2);
		
		Vector2 endPoint = velocity.copy();
		endPoint.add(entity.position);
		if(endPoint.getX() < 0 || endPoint.getX() > GameInfo.SCREEN_WIDTH) {
			endPoint.setX(-endPoint.getX());
		}
		
		if(endPoint.getY() < 0 || endPoint.getY() > GameInfo.SCREEN_HEIGHT) {
			endPoint.setY(-endPoint.getY());
		}
		
		return velocity;
	}
	
	@Override
	public String getName() {
		return "Explore";
	}

}
