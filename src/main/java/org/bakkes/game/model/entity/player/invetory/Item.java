package org.bakkes.game.model.entity.player.invetory;

import org.bakkes.game.model.AModel;
import org.newdawn.slick.Image;

public class Item extends AModel{

	private Image image;

	private int id;
	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}
}

