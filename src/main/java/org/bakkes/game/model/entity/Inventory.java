package org.bakkes.game.model.entity;

import org.bakkes.game.model.items.Item;
import org.bakkes.game.model.items.ItemCache;

public class Inventory {

	private Player owner;
	private Item[] items = new Item[10];

	public Inventory(final Player p) {
		owner = p;
	}

	public void addItem(final Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null) {
				items[i] = item;
				return;
			}
		}
		owner.showDialog("Cannot accept " + item.getName() + ", inventory is full!");
	}

	public void addItem(final int itemID) {
		addItem(ItemCache.getItemById(itemID));
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