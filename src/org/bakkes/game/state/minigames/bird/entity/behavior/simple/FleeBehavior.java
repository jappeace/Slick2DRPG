package org.bakkes.game.state.minigames.bird.entity.behavior.simple;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;

public class FleeBehavior implements IBehavior {

	private MovingEntity entity;
	public FleeBehavior(MovingEntity entity) {
		this.entity = entity;
	}
	
	public Vector2 calculate(Vector2 target) {
		Vector2 desiredVelocity = entity.position.minusOperator(target);
		desiredVelocity.normalize();
		desiredVelocity = desiredVelocity.multiply(entity.maxSpeed);
		return desiredVelocity.minusOperator(entity.velocity);
	}

	@Override
	public String getName() {
		return "Flee";
	}
	
}
