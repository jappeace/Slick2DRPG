package org.bakkes.game.model.entity;

import org.bakkes.game.model.items.Item;
import org.bakkes.game.model.items.ItemCache;

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
		owner.showDialog("Cannot accept " + item.getName() + ", inventory is full!");
	}
	
	public void addItem(int itemID) {
		addItem(ItemCache.getItemById(itemID));
	}
	
	public Item getItem(int slot) {
		if(slot >= 0 && slot < items.length && items[slot] != null)
			return items[slot];
		return null;
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
	
	public int getItemCount() {
		return items.length;
	}
	
	public boolean hasItem(int itemID) {
		return getSlot(itemID) != -1;
	}
}
