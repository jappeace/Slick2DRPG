package org.bakkes.game.state.minigames.bird;

import java.util.ArrayList;
import java.util.Random;

import org.bakkes.game.events.DialogClosed;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.Minigame;
import org.bakkes.game.state.minigames.bird.entity.Bird;
import org.bakkes.game.state.minigames.bird.entity.ControlledBird;
import org.bakkes.game.state.minigames.bird.entity.Obstacle;
import org.bakkes.game.state.minigames.bird.entity.behavior.advanced.Explore;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BirdMinigame extends Minigame {
	public static final int BIRD_MINIGAME_STATE_ID = 1;
	private static final Random random = new Random(42); //same seed for debugging purposes
	
	private int minigameStage = 0;
	
	private ControlledBird controlledBird;
	private Image backgroundImage;
	public ArrayList<Bird> birds = new ArrayList<Bird>();
	public ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	
	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		
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
							new Vector2(i * 40, i * 40),
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
		
		controlledBird = new ControlledBird(new Vector2(400, 300), 5f, new Vector2(0f, 0f),	5f,
				new Vector2(0f, 0f), 2f, new Vector2(1f, 1f), 2f, 5f, this);
		backgroundImage = new Image("res/sprites/birdminigame/bg.png");
		progress();
	}
	
	private Input prevInput;
	public void update(GameContainer gc, StateBasedGame arg1, int delta)
			throws SlickException {
		super.update(gc, arg1, delta);
		
		Input curInput = gc.getInput();
		
		if(prevInput != null) {
			if(curInput.isKeyDown(Input.KEY_SPACE) && prevInput.isKeyDown(Input.KEY_SPACE)) {
				minigameStage++;
				progress();
			}
		}
		prevInput = curInput;
		
		controlledBird.update(gc, delta);
		for(Bird bird : birds) {
			bird.getBehavior().target = controlledBird.position;
			bird.update(gc, delta);
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) 
			throws SlickException {
		backgroundImage.draw(0, 0);
		for(Bird bird : birds) {
			bird.render(gc, g);
		}
		for(Obstacle o : obstacles) {
			o.render(gc, g);
		}
		controlledBird.render(gc,  g);
		//g.drawString(Mouse.getX() + ", " + Mouse.getY(), 10, 50);
		super.render(gc, arg1, g);
	}

	@Override
	public int getID() {
		return BIRD_MINIGAME_STATE_ID;
	}
	
	public void progress() {
		switch(minigameStage) {
		case 0:
			this.showDialog("A long long time ago.\nThe only pokemon that existed were birds.");
			this.showDialog("But there was one bird, who was different.");
			this.showDialog("At first, all the other birds were afraid of him.\n(Hide behaviour)");
			this.showDialog("Use WASD to move, press space to progress in each stage.", new DialogClosed() {

				@Override
				public void onClose() {
					for(Bird b : birds) {
						b.getBehavior().addBehavior(new Explore(b));
					}
				}
				
			});
			checkDialogs();
			break;
			
		case 1:
			break;
		}
	}

}
