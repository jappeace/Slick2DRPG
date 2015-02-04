package org.bakkes.game.view.battle;

import java.nio.file.Path;

import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.IRenderable;
import org.bakkes.game.view.SpriteType;
import org.bakkes.game.view.components.Sprite;
import org.newdawn.slick.Graphics;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PokeView implements IRenderable{
    private @Inject Sprite avatar;
    private @Inject StatisticsView stats;
    private @Inject @Named("spritePokemon") Path path;

	public void setPokemon(final Pokemon to){
		avatar.setSpritePath(path.resolve(SpriteType.big.getImageName(to)));
		stats.setPokemon(to);
		final float margin = 40;
		stats.x(GameInfo.SCREEN_WIDTH - stats.width() - margin);
		stats.y(margin);
	}

	@Override
	public void render(final Graphics g) {
		avatar.render(g);
		stats.render(g);
	}


}
