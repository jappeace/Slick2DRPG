package org.bakkes.game.model.entity;

import groovy.lang.Closure;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.model.AModel;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * a entity in the overworld has to have a position (form top left of the map in pixels),
 * and offers
 */
public class OverworldEntity extends AModel implements IOverworldEntity{
	private @Named("position") @Inject Vector2f position;

	/**
	 * overwrite the regular name for a spritname,
	 * By convention use a regular name, it makes things more readable.
	 *
	 * all the pokemon are named by a number instead of a sprite, renaming them all
	 * is a bit of a choir, so for now I've implemented thsi feature
	 *
	 * Using this for example to rename a npc whilest having a different skin is a bad idea.
	 * Instead you should create a symlink (shortcut) to the desired animation so he will always have the
	 * same skin
	 */
	private String spriteName = "";

	@Override
	public Vector2f getPosition() {
		return position;
	}

	@Override
	public void setPosition(final Vector2f to){
		position = to;
	}

	@Override
	public Collection<Tile> getBlockedTiles() {
		final List<Tile> result = new LinkedList<>();
		result.add(getTile());
		return result;
	}
	@Override
	public Tile getTile() {
		return Tile.createFromPixelsCoordinates(position);
	}

	/**
	 * views should call this method instead of calling getName,
	 * getName is for showing the name as a string, this has some
	 * extra filesystem friendly modifications like killing spaces
	 */
	@Override
	public String getSpriteName() {
		if(spriteName.isEmpty()){
            spriteName = getName().toLowerCase().replace(' ', '_');
		}
		return spriteName;
	}

	@Override
	public void setSpriteName(final String to) {
		assert to != null;
		spriteName = to;
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
