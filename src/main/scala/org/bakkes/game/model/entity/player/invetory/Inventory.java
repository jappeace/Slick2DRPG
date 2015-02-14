package org.bakkes.game.model.entity.player.invetory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Inventory implements Iterable<Item>{

	private int inventorySize = 10;
	private List<Item> items = new ArrayList<>(inventorySize);
	public Inventory(){
	}
	public boolean addItem(final Item item) {
		if(items.size() < inventorySize){
            return items.add(item);
		}
		return false;
	}

	public void delete(final int slot) {
		items.remove(slot);
	}

	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}
}
