package org.bakkes.game.model.entity.player;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.model.entity.Entity;
import org.bakkes.game.model.entity.command.ICommand;
import org.bakkes.game.model.entity.command.WalkPath;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.model.pokemon.Pokemon;
import org.newdawn.slick.GameContainer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class Player extends Entity {
	@Inject protected Pokemon pokemon;
	@Inject private Inventory inventory;
	private @Inject Provider<WalkPath> walkPathProvider;
	private LinkedList<ICommand> commands = new LinkedList<>();

	private boolean hasEnteredNewTile = false;

	public Player(){
		setName("player");
	}

	public void addCommand(final ICommand command){
		commands.addLast(command);
	}
	public void moveTo(final Tile tile){
		final WalkPath command = walkPathProvider.get();
		command.setDestination(tile);
		addCommand(command);
	}
	public void update(final GameContainer gc, final int delta) {
		if(isDone()) {
			return;
		}
		if(commands.getFirst().isDone()){
			commands.removeFirst();
		}
		if(isDone()) {
			return;
		}
		commands.getFirst().execute(delta/10f);



	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void clearCommands(){
		if(!isDone()){
			final ICommand first = commands.getFirst();
			first.onInterupt();
			final List<ICommand> retain = new LinkedList<ICommand>();
			retain.add(first);
            commands.retainAll(retain);
		}
	}

	@Override
	public void onEnterNewTile(){
		hasEnteredNewTile = true;
        super.onEnterNewTile();
	}
	public boolean hasEnteredNewTile(){
		final boolean result = hasEnteredNewTile;
		hasEnteredNewTile = false;
		return result;
	}
	public boolean isDone(){
		return commands.isEmpty();
	}
}
