package org.bakkes.game.controller.init;

import org.bakkes.game.AModule;
import org.bakkes.game.model.GameInfo;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.GUIContext;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class GameContainerModule extends AModule{

	@Override
	protected void configure() {
		bind(GUIContext.class).to(GameContainer.class).in(Singleton.class);
		bind(GameContainer.class).to(AppGameContainer.class).in(Singleton.class);;
	}

	@Provides @Singleton AppGameContainer provideGameContainer(final org.newdawn.slick.Game game){
		try{
            final AppGameContainer result = new AppGameContainer(game);
            result.setDisplayMode(GameInfo.SCREEN_WIDTH, GameInfo.SCREEN_HEIGHT, false);
            result.setMaximumLogicUpdateInterval(5);
            result.setTargetFrameRate(200);
			return result;
		} catch (final SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; // crash
	}

	public @Provides @Named("canvas size") Vector2f provideCanvasSize(final GUIContext g){
        return new Vector2f(g.getWidth(), g.getHeight());
	}
	public @Provides Input provideInput(final GUIContext container){
		return container.getInput();
	}
}
