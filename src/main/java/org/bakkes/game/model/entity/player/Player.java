package org.bakkes.game.model.entity.player;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.controller.state.overworld.command.ICommand;
import org.bakkes.game.controller.state.overworld.command.WalkPath;
import org.bakkes.game.model.entity.Character;
import org.bakkes.game.model.entity.player.invetory.Inventory;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;
import org.bakkes.game.model.map.Tile;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class Player extends Character {
	private @Inject PokeBelt pokebelt;

	private @Inject Inventory inventory;
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
	public void update(final int delta) {
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

	public PokeBelt getPokebelt() {
		return pokebelt;
	}
}
