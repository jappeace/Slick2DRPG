package org.bakkes.game.state.minigames.bird.entity.behavior.advanced;

import java.util.Random;

import org.bakkes.game.GameInfo;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.math.pathfinding.heuristic.IHeuristicCalculator;
import org.bakkes.game.math.pathfinding.heuristic.ManhattanHeuristic;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.SeekBehavior;

public class ExploreBehavior implements IBehavior {
	private static final float HUNTING_DISTANCE = 30f; //hunt when within 30 pixels
	private static final Random random = new Random(42);
	private MovingEntity entity;
	private SeekBehavior seek;
	private Vector2 velocity;
	
	public ExploreBehavior(MovingEntity entity) {
		this.entity = entity;
		this.seek = new SeekBehavior(entity);
		this.velocity = new Vector2(0f, 0f);
	}
	
	public Vector2 calculate(Vector2 target) {
		//if within hunting distance
		IHeuristicCalculator distCalc = new ManhattanHeuristic();
		float dist = distCalc.getDistance(entity.position, target);
		if(Math.abs(dist) < HUNTING_DISTANCE) {
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
