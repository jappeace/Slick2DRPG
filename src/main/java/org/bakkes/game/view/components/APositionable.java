package org.bakkes.game.view.components;

import org.bakkes.game.view.IPositionable;
import org.newdawn.slick.geom.Vector2f;

public abstract class APositionable implements IPositionable{

    private final Vector2f position = new Vector2f();

    @Override
	public Vector2f getPosition(){
    	return this.position;
    }
}
