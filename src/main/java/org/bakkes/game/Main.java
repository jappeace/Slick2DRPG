package org.bakkes.game;
import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Main {

	public static void main(final String[] args) {
		initLWJGL();

		try {
			final AppGameContainer game = new AppGameContainer(new Game("Pokemons"));
			game.setDisplayMode(800, 600, false);
			game.setMaximumLogicUpdateInterval(5);
			game.setTargetFrameRate(200);
			game.start();
		} catch (final SlickException e) {
			e.printStackTrace();
		}
	}


	private static void initLWJGL() {
		final String path = new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath();
	    System.setProperty("org.lwjgl.librarypath", path);
	}
}
