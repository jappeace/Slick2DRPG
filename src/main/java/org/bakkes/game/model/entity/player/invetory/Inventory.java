package org.bakkes.game.model.entity.player.invetory;



public class Inventory {

	private Item[] items = new Item[10];

	public boolean addItem(final Item item) {
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

	public void deleteItem(final int slot) {
		if(slot >= 0 && slot < items.length)
			items[slot] = null;
	}

	public int getSlot(final int itemID) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].getId() == itemID) {
				return i;
			}
		}
		return -1;
	}

	public int getSlot(final Item item) {
		return getSlot(item.getId());
	}

	public int getItemCount() {
		return items.length;
	}

	public boolean hasItem(final int itemID) {
		return getSlot(itemID) != -1;
	}
}
