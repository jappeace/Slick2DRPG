import java.io.File;

import org.bakkes.game.Game;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Main {

	public static void main(String[] args) {
		initLWJGL();
		
		try {
			AppGameContainer game = new AppGameContainer(new Game("Pokemons"));
			game.setDisplayMode(800, 600, false);
			game.setMaximumLogicUpdateInterval(5);
			game.setTargetFrameRate(200);
			game.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	
	private static void initLWJGL() {
		String path = new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath();
	    System.setProperty("org.lwjgl.librarypath", path);
	}
}
