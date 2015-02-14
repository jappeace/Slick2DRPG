package org.bakkes.game.controller.state.overworld.command;

import org.bakkes.game.model.entity.Character;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.map.Tile;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFinder;

import com.google.inject.Inject;

public class WalkPath extends ACommand{

	private Path path;
	private int currentStep = 0;
	private boolean firstTime = true;
	private Tile destination;
	private @Inject Character entity;
	private @Inject PathFinder pathFinder;
	private final Vector2f added = new Vector2f();

	@Override
	public void execute(final float tpf) {
		if(firstTime){
			firstTime = false;
            path = pathFinder.findPath(null,
                entity.getTile().left,
                entity.getTile().top,
                getDestination().left,
                getDestination().top
            );
            if(path == null){
            	done();
            	return;
            }
		}
        final Tile destinationTile = new Tile(path.getStep(currentStep));

        final Vector2f delta = destinationTile.minus(entity.getTile()).multiply(new Vector2f(tpf, tpf));
        final Vector2f position = entity.getPosition();
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

        entity.setPosition(Tile.PixelsToGridPixels(position));
        currentStep++;
        added.x = 0;
        added.y = 0;

        if(currentStep >= path.getLength() || isInterupted()) {
        	done();
            currentStep = 0;
            entity.onFinishedWalking();
            return;
        }

        final Tile nextTile = new Tile(path.getStep(currentStep));
        entity.onEnterNewTile();

        final Tile p = new Tile(path.getStep(currentStep-1)).minus(nextTile);

        if(p.left == -1){
            entity.setFacing(Direction.East);
        }else if(p.left == 1){
            entity.setFacing(Direction.West);
        }

        if(p.top == -1){
            entity.setFacing(Direction.South);
        }else if(p.top == 1){
            entity.setFacing(Direction.North);
        }

	}

	private Tile getDestination() {
		return destination;
	}

	public void setDestination(final Tile destination) {
		this.destination = destination;
	}

}
