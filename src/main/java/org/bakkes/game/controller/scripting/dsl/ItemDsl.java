package org.bakkes.game.controller.scripting.dsl;

import org.bakkes.game.model.IHasSpriteName;
import org.bakkes.game.model.entity.player.invetory.Item;

public class ItemDsl extends ASpriteNamedDsl{
	private Item result = new Item();

	@Override
	protected IHasSpriteName getSpriteNameBean() {
		return result;
	}

	public Item getItem(){
		return result;
	}

	public void setItemName(final String itemName) {
		result.setName(itemName);
	}

}
