package org.bakkes.game.model.entity.player.invetory;

import org.bakkes.game.model.IHasSpriteName;
import org.bakkes.game.model.entity.OverworldEntity;

public class Item extends OverworldEntity implements IHasSpriteName{
	String spriteName;

	@Override
	public String getSpriteName() {
		return spriteName;
	}

	@Override
	public void setSpriteName(final String to) {
		this.spriteName = to;
	}
}

