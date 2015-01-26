package org.bakkes.game.controller.command;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.controller.state.OverworldState;
import org.bakkes.game.model.Bean;
import org.bakkes.game.model.entity.EntityTracker;
import org.bakkes.game.model.entity.npc.Person;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.entity.player.invetory.Item;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.LayerdMap;
import org.bakkes.game.model.map.Tile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

/**
 * this is a command executed once,
 * the code is really bad (nothing really happens except some delegation, and calculation)
 * so I wanted to export it to a different class
 *
 * commands work wonderfully for this cause they are aimed at the player
 *
 */
public class MoveOnOverworld extends AOneTimeCommand{

	private @Inject LayerdMap map;
	private @Inject Player player;
	private @Inject EntityTracker<Person> personTracker;
	private @Inject EntityTracker<Item> itemTracker;
	private @Inject Bean<Tile> clickedTile;
	private @Inject OverworldState state;

	private Collection<Tile> moveTo(final Tile entityFacing, final Tile entityTile){

		final Collection<Tile> result = new LinkedList<>();
        Vector2f faceCorrection = new Vector2f(3,3);
        if(entityFacing.getDirection() == Direction.North || entityFacing.getDirection() == Direction.West){
        	faceCorrection = new Vector2f(2,2); // nort and west need less correction, because they are drawn from top left, so the available tile is one less away
        }
        // first move 2 tiles away in the direction the npc is facing
		result.add(new Tile(entityFacing.multiply(faceCorrection)).plus(entityTile));
        // now move closer to garantee facing
        faceCorrection = faceCorrection.sub(new Vector2f(1,1));
        result.add(new Tile(entityFacing.multiply(faceCorrection)).plus(entityTile));


        for(final Tile tile : result){
        	if(map.isBlocked(tile)){
        		return new LinkedList<>();
        	}
        }
        return result;
	}

	@Override
	void executeOnce(final float tpf) {
		if(isInterupted()){
			return;
		}
        if(!map.isBlocked(clickedTile.getData())) {
            player.moveTo(clickedTile.getData());
            return;
        }
		if(isMoveToPerson()){
			return;
		}
		if(isMoveToItem()){
			return;
		}
		clickedTile.setData(null);
	}
	private boolean isMoveToItem(){
		final Item item = itemTracker.findEntityByTile(clickedTile.getData());
		if(item == null){
			return false;
		}
		final Tile tile = item.getTile();
		for(int i = 0; i < 360; i += 90){
			final Collection<Tile> tiles = moveTo(new Tile(new Vector2f(i)), tile);
			if(!tiles.isEmpty()){
                followPathAndInteract(tiles);
                return true;
			}

		}
		return false;
	}

	private void followPathAndInteract(final Collection<Tile> tiles){
        for(final Tile tile : tiles){
        	player.moveTo(tile);
        }
        player.addCommand(new ICommand(){
        	private boolean executed = false;
			@Override
			public void execute(final float tpf) {
				executed = true;
				state.keyPressed(Keyboard.KEY_SPACE, ' ');
				clickedTile.setData(null);
			}

			@Override
			public boolean isDone() {
				return executed;
			}

			@Override
			public void onInterupt() {
				executed = true;
			}

        });
	}
	private boolean isMoveToPerson(){
        final Person npc = personTracker.findEntityByTile(clickedTile.getData());
        if(npc == null){
        	Log.info("did not find a npc");
        	return false;
        }
        followPathAndInteract(moveTo(npc.getDirectionTile(), npc.getTile()));
        return true;
	}
}
