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
				return true;
			}

			@Override
			public void setInput(Input arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(int arg0, char arg1) {
				if(arg0 == 28) { //enter
					if(canProgress) {
						minigameStage++;
						progress();
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
		
		g.setColor(Color.black);
		g.drawString("Behaviors : (" + birds.get(0).getBehavior().size() + ")", 5, 40);
		String[] names = birds.get(0).getBehavior().getBehaviorNames();
		for(int i = 0; i < names.length; i++) {
			g.drawString("- " + names[i], 10, 40 + (i + 1) * 20);
		}
		super.render(gc, arg1, g);
	}

	@Override
	public int getID() {
		return BIRD_MINIGAME_STATE_ID;
	}
	
	public void progress() {
		System.out.println("progress");
		switch(minigameStage) {
		case 0:
			this.showDialog("A long long time ago.\nThe only pokemon that existed were birds.");
			this.showDialog("But there was one bird, who was different.");
			this.showDialog("And all the pokemon acted strange around him.");
			this.showDialog("Use WASD to move, press enter to go to the next stage.", new DialogClosed() {

				@Override
				public void onClose() {
					for(Bird b : birds) {
						b.getBehavior().addBehavior(new HideBehavior(b, obstacles));
					}
					canProgress = true;
				}
				
			});
			checkDialogs();
			break;
			
		case 1:
			this.showDialog("Separate", new DialogClosed() {

				@Override
				public void onClose() {
					for(Bird b : birds) {
						b.getBehavior().clear();
						//b.getBehavior().addBehavior(new SeekBehavior(b));
						b.getBehavior().addBehavior(new ArriveBehavior(b));
					}
					canProgress = true;
				}
			});
			checkDialogs();
			break;
		case 2:
			this.showDialog("Test", new DialogClosed() {

				@Override
				public void onClose() {
					for(Bird b : birds) {
						b.getBehavior().clear();
						//b.getBehavior().addBehavior(new SeekBehavior(b));
						b.getBehavior().addBehavior(new Explore(b));
					}
					canProgress = true;
				}
			});
			checkDialogs();
			break;
		
		}
		
		
	}

	
	public BirdMinigame getOuter() {
		return this;
	}
}
