package org.bakkes.game.controller.scripting.dsl;

import static org.bakkes.game.model.GameInfo.ANIMATION_SCRIPT_FOLDER;
import groovy.lang.Closure;

import org.bakkes.game.R;
import org.bakkes.game.controller.scripting.ScriptLoader;
import org.bakkes.game.model.ImageCache;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class AnimationDsl extends ADsl{

	private Animation[] result = new Animation[4];
	private String fileName;
	private int duration;
	private @Inject ImageCache imageCache;
	private @Inject Provider<TileDsl> tiledslProvider;
	private @Inject ScriptLoader loader;
	private Tile offset = new Tile();
	public void setFilename(final String to){
		fileName = to;
	}
	public void setDuration(final int to){
		duration = to;
	}

	public void north(final Closure commands){
		buildDirection(Direction.NORTH,commands);
	}
	public void east(final Closure commands){
		buildDirection(Direction.EAST,commands);
	}
	public void south(final Closure commands){
		buildDirection(Direction.SOUTH,commands);
	}
	public void west(final Closure commands){
		buildDirection(Direction.WEST,commands);
	}
	public void buildDirection(final int direction, final Closure commands){
        result[direction] = createAnimation();
        final TileDsl dsl = tiledslProvider.get();
        commands.setDelegate(dsl);
        commands.call();
        for(Tile tile : dsl.getResult()){
        	tile = tile.plus(offset);
            result[direction].addFrame(duration, tile.left, tile.top);
        }
	}
	private Animation createAnimation(){
		final String path = R.sprites+fileName;
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
            result[i].setPingPong(true);
        }
		return result;
	}

    /**
     * mapping tiles on a sprite sheet is a horrible job
     * this commands lets you use previous mappings as long as they use the same pattern
     */
	public void offset(final String path, final Integer left, final Integer top){
		this.offset = new Tile(left, top);
		loader.load(ANIMATION_SCRIPT_FOLDER + path + ".dsl",this);
	}

}
