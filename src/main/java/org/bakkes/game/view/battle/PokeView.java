package org.bakkes.game.view.battle;

import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.APositionedView;
import org.bakkes.game.view.SpriteType;
import org.bakkes.game.view.components.ShapeComposition;
import org.bakkes.game.view.components.Sprite;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;

public class PokeView extends APositionedView{
	private @Inject ShapeComposition view;
    private @Inject Sprite avatar;
    private @Inject StatisticsView stats;

	public void setPokemon(final Pokemon to, final SpriteType type, final Direction avatarDirection){
		avatar.setSpritePath(type.getImageName(to));
		stats.setPokemon(to);
		view.setShape(stats);
		view.put(avatarDirection, avatar);
	}

	@Override
	protected void onChangePosition(final Vector2f newLocation){
		view.x(newLocation.x);
		view.y(newLocation.y);
	}
	@Override
	public void renderView(final Graphics g) {
		view.render(g);
	}

}
