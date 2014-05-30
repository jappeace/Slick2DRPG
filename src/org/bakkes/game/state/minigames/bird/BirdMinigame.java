package org.bakkes.game.state.minigames.bird;

import java.util.ArrayList;
import java.util.Random;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.Minigame;
import org.bakkes.game.state.minigames.bird.entity.Bird;
import org.bakkes.game.state.minigames.bird.entity.Obstacle;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BirdMinigame extends Minigame {
	public static final int BIRD_MINIGAME_STATE_ID = 1;
	private static final Random random = new Random(42); //same seed for debugging purposes
	
	public ArrayList<Bird> birds = new ArrayList<Bird>();
	public ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	
	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		
		/*this.showDialog("A long long time ago");
		this.showDialog("The only pokemon that existed, were birds");
		this.showDialog("And this is how it all started...");
		checkDialogs();*/
		for(int i = 0; i < 10; i++) {
			obstacles.add(new Obstacle(
					new Vector2(random.nextInt(780), random.nextInt(580)),
					new Vector2(1f, 1f),
					20f
				));
		}
		for(int i = 0; i < 10; i++) {
			birds.add(
					new Bird(
							new Vector2(i * 20, i * 20),
							5f,
							new Vector2(0f, 0f),
							5f,
							new Vector2(0f, 0f),
							2f,
							new Vector2(1f, 1f),
							2f,
							5f,
							this
							)
					);
			
			
		}
	}
	
	public void update(GameContainer gc, StateBasedGame arg1, int delta)
			throws SlickException {
		super.update(gc, arg1, delta);
		
		Vector2 target = new Vector2(Mouse.getX(), gc.getHeight() - Mouse.getY());
		for(Bird bird : birds) {
			bird.getBehavior().target = target;
			bird.update(gc, delta);
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) 
			throws SlickException {
		for(Bird bird : birds) {
			bird.render(gc, g);
		}
		for(Obstacle o : obstacles) {
			o.render(gc, g);
		}
		g.drawString(Mouse.getX() + ", " + Mouse.getY(), 10, 50);
		super.render(gc, arg1, g);
	}

	@Override
	public int getID() {
		return BIRD_MINIGAME_STATE_ID;
	}

}
