package org.bakkes.game.model.entity.player.invetory;

import org.bakkes.game.model.AModel;
import org.bakkes.game.model.IHasSpriteName;

public class Item extends AModel implements IHasSpriteName{
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

