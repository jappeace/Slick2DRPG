package org.bakkes.game.controller.init.scripting.dsl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import groovy.lang.Closure;
import org.bakkes.game.controller.init.scripting.loader.ScriptLoader;
import org.bakkes.game.model.ImageCache;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import java.nio.file.Path;

public class AnimationDsl extends ADsl{

	private Animation[] result = new Animation[4];
	private String fileName;
	private int duration;
	private @Inject ImageCache imageCache;
	private @Inject Provider<TileDsl> tiledslProvider;
	private @Inject ScriptLoader loader;
	@Inject @Named("animations") Path animationsPath;
	@Inject @Named("sprites") Path spritePath;
	private Tile offset = new Tile();
	public void setFilename(final String to){
		fileName = to;
	}
	public void setDuration(final int to){
		duration = to;
	}

	public void north(final Closure<Void> commands){
		buildDirection(Direction.North.ordinal(),commands);
	}
	public void east(final Closure<Void> commands){
		buildDirection(Direction.East.ordinal(),commands);
	}
	public void south(final Closure<Void> commands){
		buildDirection(Direction.South.ordinal(),commands);
	}
	public void west(final Closure<Void> commands){
		buildDirection(Direction.West.ordinal(),commands);
	}
	public void buildDirection(final int direction, final Closure<Void> commands){
        result[direction] = createAnimation();
        final TileDsl dsl = tiledslProvider.get();
        commands.setDelegate(dsl);
        commands.call();
        for(Tile tile : dsl.getResult()){
        	tile = tile.plus(offset);
            result[direction].addFrame(duration, tile.left(), tile.top());
        }
	}
	private Animation createAnimation(){
		final Path path = spritePath.resolve(fileName);
		try {
			return new Animation(imageCache.load(path,32,32),new int[0],new int[0]);
		} catch (final SlickException e) {
			Log.warn("failed to load " + path);
		}
        return null; // probaly crash
	}
	public Animation[] getResult() {
		// what ever the fuck this does
        for(int i = 0; i <= result.length - 1; i++) {
        	// I think its something like go >>> <<< >>> instead of >>> >>> >>>
        	// where every > is a read direction of the tilemap
            result[i].setPingPong(true);
        }
		return result;
	}

    /**
     * mapping tiles on a sprite sheet is a horrible job
     * this commands lets you use previous mappings as long as they use the same pattern
     */
	public void offset(final String fileName, final Integer left, final Integer top){
		this.offset = new Tile(left, top);
		loader.load(animationsPath.resolve(fileName + ".dsl"),this);
	}

}
