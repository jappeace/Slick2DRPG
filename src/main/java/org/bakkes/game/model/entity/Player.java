package org.bakkes.game.model.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bakkes.game.model.entity.command.ICommand;
import org.bakkes.game.model.entity.command.WalkPath;
import org.bakkes.game.model.entity.follower.FollowingPokemon;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.scripting.PokemonManager;
import org.bakkes.game.state.OverworldState;
import org.newdawn.slick.GameContainer;

public class Player extends Entity {
	private static Random random = new Random();

	protected Pokemon pokemon;
	private Inventory inventory;
	private OverworldState game;
	private LinkedList<ICommand> commands = new LinkedList<>();

	private boolean hasEnteredNewTile = false;
	public Player(final OverworldState playingGameState) {
		game = playingGameState;
	}

	@Override
	public void init(final GameContainer gc) {
        setPosition(new Tile(60,20).topLeftPixels());
        inventory = new Inventory(this);
        final FollowingPokemon p = new FollowingPokemon(this);
        p.init(gc);
        pokemon = PokemonManager.getInstance().createPokemonByName("charmender", 5);
	}

	public void addCommand(final ICommand command){
		commands.addLast(command);
	}
	@Override
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

	public void showDialog(final String text) {
		System.out.println("Attempting to show dialog: " + text);
		game.showDialog(text);
		game.activateDialogs();
	}

	public void moveTo(final Tile toTile) {
        commands.add(new WalkPath(this, toTile));
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
