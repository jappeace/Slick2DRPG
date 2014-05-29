package org.bakkes.game.state.minigames.bird;

import java.util.ArrayList;
import java.util.Random;

import org.bakkes.game.math.Vector2;
import org.bakkes.game.state.minigames.Minigame;
import org.bakkes.game.state.minigames.bird.entity.Bird;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BirdMinigame extends Minigame {
	public static final int BIRD_MINIGAME_STATE_ID = 1;
	private static final Random random = new Random(10); //same seed for debugging purposes
	
	private ArrayList<Bird> birds = new ArrayList<Bird>();
	
	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		
		/*this.showDialog("A long long time ago");
		this.showDialog("The only pokemon that existed, were birds");
		this.showDialog("And this is how it all started...");
		checkDialogs();*/
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
							5f
							)
					);
			
		}
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		for(Bird bird : birds) {
			bird.update(arg0, arg2);
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) 
			throws SlickException {
		for(Bird bird : birds) {
			bird.render(gc, g);
		}
		super.render(gc, arg1, g);
	}

	@Override
	public int getID() {
		return BIRD_MINIGAME_STATE_ID;
	}

}
