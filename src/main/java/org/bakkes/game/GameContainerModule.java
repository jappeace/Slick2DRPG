package org.bakkes.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class GameContainerModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(GUIContext.class).to(GameContainer.class);
		bind(GameContainer.class).to(AppGameContainer.class);
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


}
