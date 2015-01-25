package org.bakkes.game.controller.scripting.dsl;

import org.bakkes.game.model.IInteractable;
import org.bakkes.game.model.entity.player.invetory.Item;

/**
 * to keeps things consistent a item (with a name) should always do the same thing
 * a npc can do different things, (because you know npc's can make choices, items are things and cannot).
 *
 * that is why items are defined outside the area and npc's inside each area
 */
public class ItemDefinitionDsl extends AInteractableDsl{
	private Item result = new Item();

	public Item getItem(){
		return result;
	}

	public void setItemName(final String itemName) {
		result.setName(itemName);
	}

	@Override
	protected Item getTarget() {
		return result;
	}

	@Override
	protected IInteractable getInteractTarget() {
		return getTarget();
	}


}
