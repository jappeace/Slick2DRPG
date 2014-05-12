package org.bakkes.game.ui;

import org.bakkes.game.entity.Inventory;
import org.bakkes.game.entity.Player;
import org.bakkes.game.items.Item;
import org.bakkes.game.math.Vector2;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class InventoryGameComponent implements DrawableGameComponent {
	private Player player;
	private int currentlySelected = 0;
	
	
	public InventoryGameComponent(Player player) {
		this.player = player;
	}
	
	public void Render(GameContainer gc, Graphics g) {
		g.setColor(Color.white);
		g.fillRect(600, 100, 180, 400);
		g.setLineWidth(5f);
		g.setColor(Color.black);
		g.drawRect(600, 100, 180, 400);
		g.resetLineWidth();
		Vector2 currentlyDrawing = new Vector2(603, 112);
		Inventory inv = player.getInventory();
		int itemIndex = 0;
		for(int i = 0; i < inv.getItemCount(); i++) {
			Item item = inv.getItem(i);
			if(item != null) {
				if(itemIndex == currentlySelected) {
					Color grayBackground = new Color(205, 201, 201, 128);
					g.setColor(grayBackground);
					g.fillRect(currentlyDrawing.getX(), currentlyDrawing.getY(), 175, 26);
					g.setColor(Color.black);
				}
				
				g.drawImage(item.getImage(), currentlyDrawing.getX(), currentlyDrawing.getY());
				g.drawString(item.getName(), currentlyDrawing.getX() + 32, currentlyDrawing.getY() + 4);
				currentlyDrawing.add(new Vector2(0, 32));
				
				itemIndex++;
			}
		}
	}
	
	public void down() {
		currentlySelected--;
		if(currentlySelected == -1)
			currentlySelected = countItemsInInventory() - 1;
	}
	
	public void up() {
		currentlySelected++;
		if(currentlySelected >= countItemsInInventory())
			currentlySelected = 0;
	}
	
	public int getCurrentlySelected() {
		return currentlySelected;
	}
	
	private int countItemsInInventory() {
		Inventory inv = player.getInventory();
		int items = 0;
		for(int i = 0; i < inv.getItemCount(); i++) {
			Item item = inv.getItem(i);
			if(item != null) {
				items++;
			}
		}
		return items;
	}

}
