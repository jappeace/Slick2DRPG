package org.bakkes.game.view;

import org.bakkes.game.AModule;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import com.google.inject.Provides;
import com.google.inject.name.Named;

/**
 * TODO: delete, is bullshit
 */
public class PositionModule extends AModule {
	private Vector2f position;

	public @Provides @Named("position") Vector2f providePosition() {
		if(position == null){
			Log.warn("position module position == null");
			return null;
		}
		return position.copy();
	}

	public PositionModule(){
		this(null);
	}
	public PositionModule(final Vector2f pos){
		position = pos;
	}
	public void setPosition(final Vector2f position) {
		this.position = position;
	}
}
