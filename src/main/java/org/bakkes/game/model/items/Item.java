package org.bakkes.game.model.items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Item {
	private int itemID;
	private String name;
	private Image image;

	public Item(final int i, final String name) {
		super();
		this.itemID = i;
		this.name = name;
	}

	public int getItemID() {
		return itemID;
	}

	public String getName() {
		return name;
	}

	public Image getImage() {
		if(image == null) {
			try {
				image = new Image("res/sprites/items/" + getItemID() + ".png");
			} catch (final SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}


}
