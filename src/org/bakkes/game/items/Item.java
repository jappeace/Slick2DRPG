package org.bakkes.game.items;

import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Item {
	
	private int itemID;
	private String name;
	private Texture image;

	public Item(int i, String name) {
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

	public Texture getImage() {
		if(image == null) {
			try {
				TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/sprites/items/" + getItemID() + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}
	
	
}
