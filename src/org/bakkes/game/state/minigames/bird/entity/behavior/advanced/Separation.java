package org.bakkes.game.state.minigames.bird.entity.behavior.advanced;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.BirdMinigame;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;

public class Separation implements IBehavior {

	private BirdMinigame minigame;
	private MovingEntity entity;
	
	public Separation(BirdMinigame mg, MovingEntity entity) {
		this.minigame = mg;
		this.entity = entity;
	}
	
	public Vector2 calculate(Vector2 target) {
		Vector2 steeringForce = new Vector2(0f, 0f);
		for(MovingEntity neighbor : minigame.birds) {
			if(neighbor != entity) {
				Vector2 toAgent = entity.position.minusOperator(neighbor.position);
				toAgent.normalize();
				toAgent = toAgent.divideBy(toAgent.length());
				steeringForce.add(toAgent);
			}
		}
		return steeringForce;
	}

}
