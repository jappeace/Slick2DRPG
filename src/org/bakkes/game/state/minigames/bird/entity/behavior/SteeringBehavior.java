package org.bakkes.game.state.minigames.bird.entity.behavior;

import java.util.ArrayList;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.entity.MovingEntity;

public class SteeringBehavior  {
	private MovingEntity entity;
	private Vector2 steeringForce;
	private ArrayList<IBehavior> behaviors;
	public Vector2 target = new Vector2(400f, 300f);
	
	public SteeringBehavior(MovingEntity entity) {
		this.entity = entity;
		this.steeringForce = new Vector2(0f, 0f);
		this.behaviors = new ArrayList<IBehavior>();
	}
	
	public void addBehavior(IBehavior behavior) {
		this.behaviors.add(behavior);
	}
	
	public void clear() {
		this.behaviors.clear();
	}
	
	public String[] getBehaviorNames() {
		String[] names = new String[behaviors.size()];
		for(int i = 0; i < behaviors.size(); i++) {
			names[i] = behaviors.get(i).getName();
		}
		return names;
	}
	
	public int size() {
		return this.behaviors.size();
	}
	
	public Vector2 calculate() {
		if(behaviors.size() == 0)
			return new Vector2(0f, 0f);
		steeringForce = new Vector2(0f, 0f);
		for(IBehavior b : behaviors) {
			steeringForce.add(b.calculate(target)); 
		}
		steeringForce = steeringForce.divideBy(behaviors.size()); //average
		return steeringForce;
	}
}
