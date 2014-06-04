package org.bakkes.game.state;

import org.bakkes.game.entity.PokemonManager;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BattleState extends CommonGameState {
	public static final int BATTLE_STATE_ID = 2;
	
	@Override
	public int getID() {
		return BATTLE_STATE_ID;
	}

	
	
	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		IPokemon pokemon = PokemonManager.getPokemonById(0);
		System.out.println(pokemon.get_name());
		System.out.println(pokemon.get_id());
	}



	@Override
	public void update(GameContainer gc, StateBasedGame arg1, int delta)
			throws SlickException {
		super.update(gc, arg1, delta);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		
		super.render(gc, arg1, g);
	}

	
	
}
