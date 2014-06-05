package org.bakkes.game.state.minigames.bird.entity.behavior.advanced;

import java.util.ArrayList;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;
import org.bakkes.game.state.minigames.bird.entity.Obstacle;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.ArriveBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.FleeBehavior;

public class HideBehavior implements IBehavior {


	
	private MovingEntity entity;
	private ArrayList<Obstacle> obstacles;
	private ArriveBehavior arrive;
	private FleeBehavior flee;
	
	public HideBehavior(MovingEntity entity, ArrayList<Obstacle> obstacles) {
		this.entity = entity;
		this.obstacles = obstacles;
		arrive = new ArriveBehavior(entity);
		flee = new FleeBehavior(entity);
	}
	
	public Vector2 calculate(Vector2 target) {
		double distToClosest = Double.MAX_VALUE;
		Vector2 bestHidingSpot = null;
		Obstacle closest;
		
		for(Obstacle curObj : obstacles) {
			Vector2 hidingSpot = getHidingPosition(curObj.position,
					curObj.boundingRadius,
					target);
			float dist = Vector2.Vec2DistanceSq(hidingSpot, entity.position);
			
			if(dist < distToClosest) {
				distToClosest = dist;
				bestHidingSpot = hidingSpot;
				closest = curObj;
			}
		}
		if(distToClosest == Double.MAX_VALUE) //no obstacles
			return flee.calculate(target);
		
		return arrive.calculate(bestHidingSpot);
	}
	
	private Vector2 getHidingPosition(Vector2 posObj, float radiusObj, Vector2 posEntity) {
		final float distanceFromBoundary = 30.0f;
		float distAway =  radiusObj + distanceFromBoundary;
		
		Vector2 toObj = posObj.copy().minusOperator(posEntity).normalizeCopy();
		Vector2 result = toObj.multiply(distAway);
		result.add(posObj);
		return result;
	}

	@Override
	public String getName() {
		return "Hide";
	}
	
}
