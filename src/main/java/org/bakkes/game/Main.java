package org.bakkes.game;
import java.io.File;

import org.bakkes.game.controller.init.GameContainerModule;
import org.bakkes.game.controller.init.GameModule;
import org.bakkes.game.model.map.WorldModule;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class Main {

	public static void main(final String[] args) throws SlickException {
		initLWJGL();

		final Injector injector = Guice.createInjector(new GameModule(), new GameContainerModule(), new WorldModule());
        injector.getInstance(AppGameContainer.class).start();
	}


	// TODO: remove and use gradle instead (I actually created symlinks because I did not know this existed I was blaming lwjgl)
	private static void initLWJGL() {
		final String path = new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath();
	    System.setProperty("org.lwjgl.librarypath", path);
	}
}
