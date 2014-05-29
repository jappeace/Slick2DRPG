package org.bakkes.game.state.minigames.bird.entity.behavior;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;

public class ArriveBehavior implements IBehavior {

	private MovingEntity entity;
	public ArriveBehavior(MovingEntity entity) {
		this.entity = entity;
	}
	
	public Vector2 calculate(Vector2 target) {
		Vector2 toTarget = target.minusOperator(entity.position);
		float dist = toTarget.length();
		if(dist > 0) {
			final float decelTweaker = 0.3f;
			float speed = dist / decelTweaker;
			speed = Math.min(speed, entity.maxSpeed);
			Vector2 desiredVelocity = toTarget.multiply(speed).divideBy(dist);
			return desiredVelocity.minusOperator(entity.velocity);
		}
		return new Vector2(0, 0);
	}

}
