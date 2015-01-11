package org.bakkes.game.model.entity;

import org.newdawn.slick.GameContainer;


public class NPC extends Entity {

	protected int id;


	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Override
	public void update(final GameContainer gc, final int delta) {
	}
}
