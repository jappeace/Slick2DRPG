package org.bakkes.game.view.battle;

import java.nio.file.Path;

import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.SpriteType;
import org.bakkes.game.view.components.AShape;
import org.bakkes.game.view.components.ShapeComposition;
import org.bakkes.game.view.components.Sprite;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BattlePokeView extends AShape{
    private @Inject Sprite avatar;
    private @Inject StatisticsView stats;
    private @Inject ShapeComposition view;
    private @Inject @Named("spritePokemon") Path path;

	public void setPokemon(final Pokemon to, final SpriteType type){
		avatar.setSpritePath(path.resolve(type.getImageName(to)));
		stats.setPokemon(to);
		final float margin = 40;
		stats.x(GameInfo.SCREEN_WIDTH - stats.width() - margin);
		stats.y(margin);
		view.setShape(stats);
		view.put(Direction.East, avatar);
		x(x()); // update position of subshapes
	}

	@Override
	public void render(final Graphics g) {
		view.render(g);
	}

    @Override
	public void onChangePosition(final Vector2f position){
    	view.setPosition(position);
    }

	@Override
	public float width() {
		return view.width();
	}

	@Override
	public float height() {
		return view.height();
	}

}
