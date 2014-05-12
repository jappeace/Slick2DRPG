package org.bakkes.game.items;

import java.util.HashMap;

public class ItemCache {
	private static HashMap<Integer, Item> itemCache = new HashMap<Integer, Item>();
	
	//items are hardcoded for now
	static {
		itemCache.put(0, new Item(0, "PokeBall"));
		itemCache.put(1, new Item(1, "Greater Ball"));
		itemCache.put(2, new Item(2, "Pokemon Egg"));
	}
	
	public static Item getItemById(int id) {
		return itemCache.get(id);
	}
}
