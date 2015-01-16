package org.bakkes.game.view.overworld;

import org.bakkes.game.R;
import org.bakkes.game.model.ImageCache;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.entity.player.invetory.Inventory;
import org.bakkes.game.model.entity.player.invetory.Item;
import org.bakkes.game.view.AView;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

public class InventoryView extends AView {
	private static final Color ITEM_HIGHLIGHTED_COLOR = new Color(205, 201, 201, 128);
	private static final Color INVENTORY_BACKGROUND_COLOR = new Color(0, 0, 0, 128);
	private static final Color INVENTORY_OUTLINE_COLOR = new Color(255, 255, 255);
	private @Inject ImageCache images;

	private @Inject Player player;
	private int currentlySelected = 0;

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

	@Override
	protected void renderView(final Graphics g) {
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

				final String path =R.itemSprites + item.getId() + ".png";
				try {
					g.drawImage(images.load(path), currentlyDrawing.getX(), currentlyDrawing.getY());
				} catch (final SlickException e) {
					Log.warn("failed drwaing " + path);
				}
				g.drawString(item.getName(), currentlyDrawing.getX() + 32, currentlyDrawing.getY() + 4);
				currentlyDrawing.add(new Vector2f(0, 32));

				itemIndex++;
			}
		}
	}

}
