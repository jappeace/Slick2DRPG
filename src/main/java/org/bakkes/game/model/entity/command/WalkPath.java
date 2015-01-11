package org.bakkes.game.model.entity.command;

import org.bakkes.game.World;
import org.bakkes.game.model.entity.Entity;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.Path;

public class WalkPath implements ICommand{

	private Path path;
	private int currentStep = 0;
	private boolean isDone = false;
	private boolean firstTime = true;
	private final Tile destination;
	private final Entity player;
	private final Vector2f added = new Vector2f();
	private boolean interupted = false;
	public WalkPath(final Entity player, final Tile tile){
		this.player = player;
		destination = tile;
	}
	@Override
	public void execute(final float tpf) {
		if(firstTime){
			firstTime = false;
            path = World.getPathFinder().findPath(null,
                player.getTile().left,
                player.getTile().top,
                destination.left,
                destination.top
            );
            if(path == null){
            	isDone = true;
            	return;
            }
		}
        final Tile destinationTile = new Tile(path.getStep(currentStep));

        final Vector2f delta = destinationTile.minus(player.getTile()).multiply(new Vector2f(tpf, tpf));
        final Vector2f position = player.getPosition();
        if(delta.equals(new Vector2f(0,0))){
            if(destinationTile.topLeftPixels().x < position.x){
                delta.x = -tpf;
            }
            if(destinationTile.topLeftPixels().y < position.y){
                delta.y = -tpf;
            }
        }

        boolean arrived = false;
        if(Math.abs(added.x + delta.x) >= Tile.WIDTH) {
        	final float smoothDistance = Tile.WIDTH - Math.abs(added.x) +0.01f;
            delta.x = delta.x >= 0 ?  smoothDistance: -smoothDistance;
            arrived = true;
        }

        if(Math.abs(added.y + delta.y) >= Tile.HEIGHT) {
        	final float smoothDistance = Tile.HEIGHT - Math.abs(added.y) +0.01f;
            delta.y = delta.y >= 0 ? smoothDistance : -smoothDistance;
            arrived = true;
        }

        added.add(delta);
        position.add(delta);

        if(delta.x < 0 || delta.y < 0){
        	if(!arrived){
        		return;
        	}
        }
        if(!destinationTile.contains(position)) {

        	return;
        }

        player.setPosition(Tile.PixelsToGridPixels(position));
        currentStep++;
        added.x = 0;
        added.y = 0;

        if(currentStep >= path.getLength() || interupted) {
            isDone = true;
            currentStep = 0;
            player.onFinishedWalking();
            return;
        }

        final Tile nextTile = new Tile(path.getStep(currentStep));
        player.onEnterNewTile();

        final Tile p = new Tile(path.getStep(currentStep-1)).minus(nextTile);

        if(p.left == -1){
            player.setFacing(Direction.EAST);
        }else if(p.left == 1){
            player.setFacing(Direction.WEST);
        }

        if(p.top == -1){
            player.setFacing(Direction.SOUTH);
        }else if(p.top == 1){
            player.setFacing(Direction.NORTH);
        }

	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return isDone;
	}
	@Override
	public void onInterupt() {
		interupted = true;
	}

}
