package org.bakkes.game.model.entity;

import org.bakkes.game.model.IHasSpriteName;
import org.bakkes.game.model.IModel;
import org.bakkes.game.model.map.IBlocksTiles;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.geom.Vector2f;

public interface IOverworldEntity extends IHasSpriteName, IModel, IBlocksTiles{

	public Vector2f getPosition();

	public void setPosition(final Vector2f to);

	public Tile getTile();
}
