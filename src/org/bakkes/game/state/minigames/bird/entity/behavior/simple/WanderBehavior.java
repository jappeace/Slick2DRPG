package org.bakkes.game.state.minigames.bird.entity.behavior.simple;

import java.util.Random;

import org.bakkes.game.GameInfo;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;

/*
 * Not working
 */
public class WanderBehavior implements IBehavior {
	private static final Random random = new Random(10);
	
	private MovingEntity entity;
	private Vector2 wanderTarget;
	private float wanderRadius = 50f;
	private float wanderDistance = 2f;
	public WanderBehavior(MovingEntity entity) {
		this.entity = entity;
		float theta = (float) (random.nextFloat() * (Math.PI * 2));

		 //create a vector to a target position on the wander circle
		wanderTarget = new Vector2((float)(wanderRadius * Math.cos(theta)),
				(float)(wanderRadius * Math.sin(theta)));
	}
	
	public Vector2 calculate(Vector2 targetUnused) {
		float jitterThisTimeSlice = 0.1f * GameInfo.getInstance().delta;
		wanderTarget.add(new Vector2(random.nextFloat() - random.nextFloat() * jitterThisTimeSlice,
				random.nextFloat() - random.nextFloat() * jitterThisTimeSlice));
		wanderTarget.normalize();
		wanderTarget.multiply(wanderRadius);
		Vector2 target = wanderTarget.minusOperator(new Vector2(wanderDistance, 0));
		//target = pointToWorldSpace(target, entity.heading, entity.side, entity.position);
		return target.minusOperator(entity.position);
	}
	
}
