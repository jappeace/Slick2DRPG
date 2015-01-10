package org.bakkes.game.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class NPC extends Entity {

	private int id;


	@Override
	public void update(final GameContainer gc, final int delta) {
	}

	@Override
	public void render(final GameContainer gc, final Graphics g) {
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

}
