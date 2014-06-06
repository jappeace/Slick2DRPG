package org.bakkes.game.state.minigames.bird;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bakkes.game.GameInfo;
import org.bakkes.game.events.DialogClosed;
import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.Minigame;
import org.bakkes.game.state.minigames.bird.entity.Bird;
import org.bakkes.game.state.minigames.bird.entity.ControlledBird;
import org.bakkes.game.state.minigames.bird.entity.Obstacle;
import org.bakkes.game.state.minigames.bird.entity.behavior.IBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.advanced.ExploreBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.advanced.HideBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.advanced.Separation;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.ArriveBehavior;
import org.bakkes.game.state.minigames.bird.entity.behavior.simple.SeekBehavior;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BirdMinigame extends Minigame {
	public static final int BIRD_MINIGAME_STATE_ID = 1;
	private static final Random random = new Random(42); //same seed for debugging purposes
	
	private boolean canProgress = true;
	private int minigameStage = 0;
	
	private ControlledBird controlledBird;
	private Image backgroundImage;
	public ArrayList<Bird> birds = new ArrayList<Bird>();
	public ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		
		this.showDialog("Welcome to the birdsimulator 2.0!\nAt the top you will see the available behaviors and the keys to toggle them, "
				+ "\nplease use them wisely because we recently had some reports of animal abuse...");
		this.showDialog("No seriously, we love those little birds.");
		this.showDialog("Maybe if we really loved them, we shouldn't have implanted chips in their brains.\n"
				+ "Oh well, go ahead!\n"
				+ "Before I forget, you can control the dragonite with WASD. \nYou can't use the arrow keys, because someone spilled their drink over it and now\n"
				+ "they're stuck.");
		this.showDialog("One last thing, press ESC when you're finished.");
		this.checkDialogs();
		
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
		
		
		gc.getInput().addKeyListener(new KeyListener() {

			@Override
			public void inputEnded() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void inputStarted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isAcceptingInput() {
				// TODO Auto-generated method stub
				return GameInfo.getInstance().stateGame.getCurrentStateID() == getID();
			}

			@Override
			public void setInput(Input arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(int arg0, char arg1) {
				System.out.println("Key " + arg0);
				if(arg0 >= 2 && arg0 <= 6) { //key between 1 and 5
					for(Bird b : birds) {
						b.toggle(arg0 - 2);
					}
				}
			}

			@Override
			public void keyReleased(int arg0, char arg1) {

			}
			
		});
		
	}
	

	public void update(GameContainer gc, StateBasedGame arg1, int delta)
			throws SlickException {
		super.update(gc, arg1, delta);
		
		
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
		
		
		HashMap<Integer, Boolean> enabled = birds.get(0).getEnabledBehaviors();
		HashMap<Integer, IBehavior> available = birds.get(0).getAvailableBehaviors();
		for(int i = 0; i < enabled.size(); i++) {
			if(enabled.get(i)) {
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
			g.drawString(available.get(i).getName(), 120 + (80 * i), 20);
			g.drawString("Key: " + (i + 1), 120 + (80 * i), 35);
		}
		g.setColor(Color.black);
		
		super.render(gc, arg1, g);
	}

	@Override
	public int getID() {
		return BIRD_MINIGAME_STATE_ID;
	}

	
	public BirdMinigame getOuter() {
		return this;
	}
}
