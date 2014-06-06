package org.bakkes.game.state.minigames.bird.entity;

import java.util.ArrayList;
import java.util.HashMap;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.BirdMinigame;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.SteeringBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.advanced.ExploreBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.advanced.HideBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.advanced.Separation;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.ArriveBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.FleeBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.SeekBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.WanderBehavior;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Bird extends MovingEntity {
	private static final float MOVEMENT_PER_MS = 0.01f; //move max 0.1f per MS
	
	private SpriteSheet birdSheet;
	private Animation currentAnimation;
	private Animation birdLeft;
	private Animation birdRight;
	private SteeringBehavior behavior;
	
	public HashMap<Integer, Boolean> enabledBehaviors = new HashMap<Integer, Boolean>();
	public HashMap<Integer, IBehavior> availableBehaviors = new HashMap<Integer, IBehavior>();
	
	public Bird(Vector2 position, float radius, Vector2 velocity,
			float maxSpeed, Vector2 heading, float mass, Vector2 scale,
			float turnRate, float maxForce, BirdMinigame minigame) {
		
		super(position, radius, velocity, maxSpeed, heading, mass, scale, turnRate,
				maxForce);
		
		behavior = new SteeringBehavior(this);
		//behavior.addBehavior(new HideBehavior(this, minigame.obstacles));
		//behavior.addBehavior(new Explore(this));
		//behavior.addBehavior(new HideBehavior(this, minigame.obstacles));
		
		availableBehaviors.put(0, new ArriveBehavior(this));
		availableBehaviors.put(1, new FleeBehavior(this));
		availableBehaviors.put(2, new SeekBehavior(this));
		availableBehaviors.put(3, new ExploreBehavior(this));
		availableBehaviors.put(4, new HideBehavior(this, minigame.obstacles));
		for(int i = 0; i < availableBehaviors.size(); i++) {
			enabledBehaviors.put(i, false);
		}
		try {
			birdSheet = new SpriteSheet("res/sprites/birdminigame/bird.png", 40, 40);
			birdLeft = new Animation(birdSheet, 0, 0, 2, 0, true, 150, true);
			birdRight = new Animation(birdSheet, 0, 1, 2, 1, true, 150, true);
			birdLeft.setPingPong(true);
			birdRight.setPingPong(true);
			currentAnimation = birdRight;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void toggle(int id) {
		enabledBehaviors.put(id, !enabledBehaviors.get(id));
		updateBehaviors();
	}
	
	public void enable(int id) {
		enabledBehaviors.put(id, true);
		updateBehaviors();
	}
	
	public void disable(int id) {
		enabledBehaviors.put(id, false);
		updateBehaviors();
	}
	
	private void updateBehaviors() {
		behavior.clear();
		boolean anyEnabled = false;
		for(int i = 0; i < enabledBehaviors.size(); i++) { //if there are no behaviors left, reset velocity
			if(enabledBehaviors.get(i)) {
				anyEnabled = true;
				break;
			}
		}
		if(!anyEnabled) {
			this.velocity = new Vector2(0, 0);
		}
		
		for(int i = 0; i < enabledBehaviors.size(); i++) {
			if(enabledBehaviors.get(i)) {
				behavior.addBehavior(availableBehaviors.get(i));
			}
		}
	}
	
	public SteeringBehavior getBehavior() {
		return behavior;
	}
	
	public HashMap<Integer, Boolean> getEnabledBehaviors() {
		return enabledBehaviors;
	}
	
	public HashMap<Integer, IBehavior> getAvailableBehaviors () {
		return availableBehaviors;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		Vector2 oldPos = position.copy();
		Vector2 steeringForce = behavior.calculate();
		Vector2 acceleration = steeringForce.divideBy(mass);
		velocity.add(acceleration.multiply(delta * MOVEMENT_PER_MS));
		velocity.truncate(maxSpeed);
		position.add(velocity.multiply(delta * MOVEMENT_PER_MS));
		this.heading = velocity.normalizeCopy();
		this.side = this.heading.perp();
		if(velocity.getX() > 0) {
			currentAnimation = birdRight;
		} else if(velocity.getX() < 0) { //do this so if velocity is 0, it stays at the last animation
			currentAnimation = birdLeft;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.white);
		//g.drawRect(position.getX(), position.getY(), 5, 5);
		g.drawAnimation(currentAnimation, position.getX(), position.getY());
	}

}
