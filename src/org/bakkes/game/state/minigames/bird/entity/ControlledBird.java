package org.bakkes.game.state.minigames.bird.entity;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.bird.BirdMinigame;
import org.bakkes.game.state.minigames.bird.entity.behavior.SteeringBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.advanced.HideBehavior;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ControlledBird extends MovingEntity  {
	private SpriteSheet birdSheet;
	private Animation currentAnimation;
	private Animation birdLeft;
	private Animation birdRight;
	
	public ControlledBird(Vector2 position, float radius, Vector2 velocity,
			float maxSpeed, Vector2 heading, float mass, Vector2 scale,
			float turnRate, float maxForce, BirdMinigame minigame) {
		
		super(position, radius, velocity, maxSpeed, heading, mass, scale, turnRate,
				maxForce);
		try {
			birdSheet = new SpriteSheet("res/sprites/birdminigame/controlledbird.png", 60, 40);
			birdLeft = new Animation(birdSheet, 0, 1, 2, 1, true, 150, true);
			birdRight = new Animation(birdSheet, 0, 0, 2, 0, true, 150, true);
			birdLeft.setPingPong(true);
			birdRight.setPingPong(true);
			currentAnimation = birdRight;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final float MOVEMENT_MULTIPLIER = 0.22f;
	
	@Override
	public void update(GameContainer gc, int delta) {
		if(gc.getInput().isKeyDown(Input.KEY_W) && position.getY() > 0) {
			position.setY(position.getY() - (delta * MOVEMENT_MULTIPLIER));
		}
		if(gc.getInput().isKeyDown(Input.KEY_A) && position.getX() > 0) {
			currentAnimation = birdLeft;
			position.setX(position.getX() - (delta * MOVEMENT_MULTIPLIER));
		}
		if(gc.getInput().isKeyDown(Input.KEY_D) && position.getX() < 740) { //800 - width
			currentAnimation = birdRight;
			position.setX(position.getX() + (delta * MOVEMENT_MULTIPLIER));
		}
		if(gc.getInput().isKeyDown(Input.KEY_S) && position.getY() < 560) { //600 - height
			position.setY(position.getY() + (delta * MOVEMENT_MULTIPLIER)); 
		}
		/* disable gravity
		if(position.getY() < 560) {
			position.setY(position.getY() + (delta * MOVEMENT_MULTIPLIER));  //gravity
		}*/
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.drawAnimation(currentAnimation, position.getX(), position.getY());
	}
	
}
