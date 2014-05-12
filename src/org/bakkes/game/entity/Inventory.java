package org.bakkes.game.entity;

import org.bakkes.game.items.Item;

public class Inventory {

	private Player owner;
	private Item[] items = new Item[10];
	
	public Inventory(Player p) {
		owner = p;
	}
	
	public void addItem(Item item) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null) {
				items[i] = item;
				return;
			}
		}
		owner.sendMessage("Cannot accept " + item.getName() + ", inventory is full!");
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
