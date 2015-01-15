package org.bakkes.game.model.items;

import org.bakkes.game.model.AModel;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Item extends AModel{

	private String name;
	private Image image;

	private int id;
	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}
	public Item(final int i, final String name) {
		super();
		setId(i);
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public Image getImage() {
		if(image == null) {
			try {
				image = new Image("res/sprites/items/" + getId() + ".png");
			} catch (final SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}


}
