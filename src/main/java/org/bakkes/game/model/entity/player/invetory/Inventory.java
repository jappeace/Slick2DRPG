package org.bakkes.game.model.entity.player.invetory;



public class Inventory {

	private Item[] items = new Item[10];

	public synchronized boolean addItem(final Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null) {
				items[i] = item;
				return true;
			}
		}
		return false;
	}

	public Item getItem(final int slot) {
		if(slot >= 0 && slot < items.length && items[slot] != null)
			return items[slot];
		return null;
	}

	public synchronized void deleteItem(final int slot) {
		if(slot >= 0 && slot < items.length)
			items[slot] = null;
	}

	public int getItemCount() {
		return items.length;
	}

}
