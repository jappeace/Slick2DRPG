package org.bakkes.game.entity.follower;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface IFollower {
	void update(int delta);
	void face(int direction);
	void render(GameContainer gc, Graphics g);
}
