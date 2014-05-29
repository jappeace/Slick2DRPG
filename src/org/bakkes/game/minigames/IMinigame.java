package org.bakkes.game.minigames;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface IMinigame {
	void activate(GameContainer gc);
	void update(GameContainer gc, int delta);
	void render(GameContainer gc, Graphics g);
}
