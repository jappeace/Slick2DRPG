package org.bakkes.game.model.entity;

import groovy.lang.Closure;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.model.ASpriteNamedModel;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

/**
 * a entity in the overworld has to have a position (form top left of the map in pixels),
 * and offers
 */
public class OverworldEntity extends ASpriteNamedModel implements IOverworldEntity{
	private Vector2f position;

	@Override
	public Vector2f getPosition() {
		return position;
	}

	/**
	 * in pixels
	 */
	@Override
	public void setPosition(final Vector2f to){
		position = to;
	}

	@Override
	public void setPosition(final Tile to){
		position = to.topLeftPixels();
	}

	/**
	 * here we sort of assume all entities block 4 tiles
	 * the getTile(), and the one left below and leftbelow of it
	 */
	@Override
	public Collection<Tile> getBlockedTiles() {
		final List<Tile> result = new LinkedList<>();
		result.add(getTile());
		result.add(getTile().plus(new Tile(1,0)));
		result.add(getTile().plus(new Tile(0,1)));
		result.add(getTile().plus(new Tile(1,1)));
		return result;
	}
	@Override
	public Tile getTile() {
		return Tile.createFromPixelsCoordinates(position);
	}

    private Closure<Void> onInteract = null;
    @Override
	public void interact(){
        Log.info("interacting with " + getName() + " on location: " + getPosition());
        if(onInteract == null){
            Log.info("no interaction present");
            return;
        }
        onInteract.call();
    }

    @Override
	public void setInteract(final Closure<Void> callback){
        onInteract = callback;
    }
}
