package org.bakkes.game.view;

import org.bakkes.game.model.entity.Inventory;
import org.bakkes.game.model.entity.Player;
import org.bakkes.game.model.items.Item;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class InventoryGameComponent implements IRenderable {
	private static final Color ITEM_HIGHLIGHTED_COLOR = new Color(205, 201, 201, 128);
	private static final Color INVENTORY_BACKGROUND_COLOR = new Color(0, 0, 0, 128);
	private static final Color INVENTORY_OUTLINE_COLOR = new Color(255, 255, 255);

	private Player player;
	private int currentlySelected = 0;

	public InventoryGameComponent(final Player player) {
		this.player = player;
	}

	@Override
	public void render(final GameContainer gc, final Graphics g) {
		g.setColor(INVENTORY_BACKGROUND_COLOR);
		g.fillRect(600, 100, 180, 400);
		g.setLineWidth(5f);
		g.setColor(INVENTORY_OUTLINE_COLOR);
		g.drawRect(600, 100, 180, 400);
		g.resetLineWidth();
		final Vector2f currentlyDrawing = new Vector2f(603, 112);
		final Inventory inv = player.getInventory();
		int itemIndex = 0;
		for(int i = 0; i < inv.getItemCount(); i++) {
			final Item item = inv.getItem(i);
			if(item != null) {
				if(itemIndex == currentlySelected) {
					g.setColor(ITEM_HIGHLIGHTED_COLOR);
					g.fillRect(currentlyDrawing.getX(), currentlyDrawing.getY(), 175, 26);
					g.setColor(INVENTORY_OUTLINE_COLOR);
				}

				g.drawImage(item.getImage(), currentlyDrawing.getX(), currentlyDrawing.getY());
				g.drawString(item.getName(), currentlyDrawing.getX() + 32, currentlyDrawing.getY() + 4);
				currentlyDrawing.add(new Vector2f(0, 32));

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
		final Inventory inv = player.getInventory();
		int items = 0;
		for(int i = 0; i < inv.getItemCount(); i++) {
			final Item item = inv.getItem(i);
			if(item != null) {
				items++;
			}
		}
		return items;
	}

}
