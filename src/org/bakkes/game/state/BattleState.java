package org.bakkes.game.state;

import org.bakkes.game.battle.Battle;
import org.bakkes.game.battle.IMove;
import org.bakkes.game.entity.PokemonManager;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BattleState extends CommonGameState {
	public static final int BATTLE_STATE_ID = 2;
	
	private Battle battle;
	private Image playerImage;
	private Image enemyImage;
	
	@Override
	public int getID() {
		return BATTLE_STATE_ID;
	}
	
	public void setBattle(Battle b) {
		this.battle = b;
		try {
			playerImage = new Image("/res/sprites/pokemon/" + b.getPlayer().getPokemon().get_image());
			enemyImage = new Image("/res/sprites/pokemon/" + b.getEnemy().get_image());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame arg1, int delta)
			throws SlickException {
		super.update(gc, arg1, delta);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		g.drawImage(enemyImage, 380f, 10f);
		g.drawString("Enemy moves:", 360f, 85f);
		IMove[] moves = battle.getEnemy().get_moves();
		for(int i = 0; i < moves.length; i++) {
			g.drawString(moves[i].get_name() + ": " + moves[i].get_desirability(battle.getEnemy(), battle.getPlayer().getPokemon()), 360f, 100f + (i * 15));
		}
		super.render(gc, arg1, g);
	}

	
	
}
