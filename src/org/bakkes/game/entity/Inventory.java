package org.bakkes.game.entity;

import org.bakkes.game.Item;

public class Inventory {

	private Item[] items = new Item[10];
	
	public void addItem(Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null) {
				items[i] = item;
				return;
			}
		}
		//send message inventory is full
	}
	
	public void deleteItem(int slot) {
		if(slot >= 0 && slot < items.length)
			items[slot] = null;
	}
	
	public int getSlot(int itemID) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].getItemID() == itemID) {
				return i;
			}
		}
		return -1;
	}
	
	public int getSlot(Item item) {
		return getSlot(item.getItemID());
	}
}
