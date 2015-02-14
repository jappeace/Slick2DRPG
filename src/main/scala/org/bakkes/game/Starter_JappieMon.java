package org.bakkes.game;
import java.io.File;

import org.bakkes.game.controller.async.ThreadModule;
import org.bakkes.game.controller.init.GameContainerModule;
import org.bakkes.game.controller.init.GameModule;
import org.bakkes.game.controller.init.scripting.SpeciesModule;
import org.bakkes.game.controller.state.StateModule;
import org.bakkes.game.controller.state.battle.BattleModule;
import org.bakkes.game.controller.state.battle.BattleStateModule;
import org.bakkes.game.controller.state.overworld.OverworldModule;
import org.bakkes.game.model.entity.npc.PeopleAreaModule;
import org.bakkes.game.model.entity.player.PlayerModule;
import org.bakkes.game.model.entity.player.invetory.ItemAreaModule;
import org.bakkes.game.model.font.FontModule;
import org.bakkes.game.model.map.MapModule;
import org.bakkes.game.view.ViewModule;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class Starter_JappieMon {

	public static void main(final String[] args) throws SlickException {
		initLWJGL();

		final Injector injector = Guice.createInjector(
				new GameModule(),
				new GameContainerModule(),

				new ThreadModule(),
				new PathsModule(),
				new ViewModule(),
				new FontModule(),

				new StateModule(),
				new OverworldModule(),
				new MapModule(),
				new BattleStateModule(),

				new BattleModule(),
				new PeopleAreaModule(),
				new ItemAreaModule(),

				new PlayerModule(),
				new SpeciesModule()
            );
        injector.getInstance(AppGameContainer.class).start();
	}


	// TODO: remove and use gradle instead (I actually created symlinks because I did not know this existed I was blaming lwjgl)
	private static void initLWJGL() {
		final String path = new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath();
	    System.setProperty("org.lwjgl.librarypath", path);
	}
}
