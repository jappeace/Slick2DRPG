package org.bakkes.game.state.minigames.bird.entity.behavior;

import org.bakkes.game.entity.Entity;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;

public class SeekBehavior implements IBehavior {
	
	private MovingEntity entity;
	public SeekBehavior(MovingEntity entity) {
		this.entity = entity;
	}
	
	public Vector2 calculate(Vector2 target) {
		Vector2 desiredVelocity = target.minusOperator(entity.position);
		desiredVelocity.normalize();
		desiredVelocity = desiredVelocity.multiply(entity.maxSpeed);
		return desiredVelocity.minusOperator(entity.velocity);
	}

}
