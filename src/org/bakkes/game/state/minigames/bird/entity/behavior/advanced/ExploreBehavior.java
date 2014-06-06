package org.bakkes.game.state.minigames.bird.entity.behavior.advanced;

import java.util.Random;

import org.bakkes.game.GameInfo;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.math.pathfinding.heuristic.EuclideanDistance;
import org.bakkes.game.math.pathfinding.heuristic.IHeuristicCalculator;
import org.bakkes.game.math.pathfinding.heuristic.ManhattanHeuristic;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.SeekBehavior;

public class ExploreBehavior implements IBehavior {
	private static final float HUNTING_DISTANCE = 130f; //hunt when within 30 pixels
	private static final Random random = new Random(42);
	private MovingEntity entity;
	private SeekBehavior seek;
	private Vector2 velocity;
	private Vector2 destination;
	private int lastDest = 5;
	private boolean destInverted = false;
	private boolean currentX = true;
	public ExploreBehavior(MovingEntity entity) {
		this.entity = entity;
		this.seek = new SeekBehavior(entity);
		this.velocity = new Vector2(0f, 0f);
		this.destination = entity.position.copy();
		setNextDestination();
	}
	
	private void setNextDestination() {
		lastDest ++;
		if(lastDest == 70) {
			lastDest = 0;
			destInverted = !destInverted;
		}
		if(lastDest == 35) {
			currentX = !currentX;
			
		}
		int xDiff = destInverted ? 5 : -5;
		int yDiff = destInverted ? 5 : -5;
		
		if(currentX)
			yDiff = 0;
		else
			xDiff = 0;
		//this.destination = entity.position.copy();
		this.destination.add(new Vector2(xDiff, yDiff));
	}
	
	public Vector2 calculate(Vector2 target) {
		//if within hunting distance
		IHeuristicCalculator distCalc = new ManhattanHeuristic();
		float dist = distCalc.getDistance(entity.position, target);
		if(Math.abs(dist) < HUNTING_DISTANCE) {
			return seek.calculate(target);
		}
		
		float distToDest = distCalc.getDistance(entity.position, destination);
		if(Math.abs(distToDest) < 2) {
			setNextDestination();
		}
		return seek.calculate(destination);
	}
	
	@Override
	public String getName() {
		return "Explore";
	}

}
