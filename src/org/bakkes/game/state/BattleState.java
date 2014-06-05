package org.bakkes.game.state;

import java.util.ArrayList;

import org.bakkes.game.battle.Battle;
import org.bakkes.game.battle.IMove;
import org.bakkes.game.entity.PokemonManager;
import org.bakkes.game.scripting.interfaces.IPokemon;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BattleState extends CommonGameState {
	public static final int BATTLE_STATE_ID = 2;
	
	private Battle battle;
	private Image playerImage;
	private Image enemyImage;
	private int selectedMove = 0;
	private boolean firstRun = true;
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
		firstRun = true;
	}
	
	private void selectMove(int selected) {
		IMove[] moves = battle.getPlayer().getPokemon().get_moves();
		System.out.println(selectedMove + " - " + moves.length);
		if(selected >= moves.length)
			selectedMove = 0;
		else if(selected < 0)
			selectedMove = moves.length - 1;
		else
			selectedMove = selected;
		System.out.println(selectedMove + " - " + moves.length);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		System.out.println("Got init");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame arg1, int delta)
			throws SlickException {
		if(firstRun) {
			gc.getInput().isKeyPressed(Input.KEY_ENTER);//reset ENTER key state
			firstRun = false;
		}
		if(battle.isOver()) {
			if(gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
				selectedMove = -1;
				arg1.enterState(PlayingGameState.PLAYING_STATE_ID);
			}
			return;
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
			System.out.println("hi " + selectedMove);
			selectMove(selectedMove + 1);
		} else if(gc.getInput().isKeyPressed(Input.KEY_UP)) {
			System.out.println("ha " + selectedMove);
			selectMove(selectedMove - 1);
		}
		if(gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			battle.executeMove(battle.getPlayer().getPokemon().get_moves()[selectedMove], true);
			
			if(battle.isOver())
				return;
			
			//now execute opponents move
			IMove[] enemyMoves = battle.getEnemy().get_moves();
			IMove bestMove = null;
			float bestDesirability = -Float.MAX_VALUE;
			for(int i = 0; i < enemyMoves.length; i++) { //get the best move from the opponent
				float desirability = enemyMoves[i].get_desirability(battle.getEnemy(), battle.getPlayer().getPokemon());
				if(desirability > bestDesirability) {
					bestMove = enemyMoves[i];
					bestDesirability = desirability;
				}
			}
			battle.executeMove(bestMove, false);
		}
		
		super.update(gc, arg1, delta);
	}
	private float leftOffset = 20f;
	private int showCount = 30;
	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.setColor(new Color(255, 255, 255, 255));
		g.setLineWidth(5f);
		
		if(battle.isOver() && battle.hasPlayerWon()) { //player won, don't show enemy stuff
			g.drawString("You were victorious! Press enter to leave", leftOffset, 150f);
		} else {
			g.drawImage(enemyImage, leftOffset + 260f, 10f);
			g.drawString("Enemy moves:", leftOffset + 260f, 85f);
			IMove[] moves = battle.getEnemy().get_moves();
			for(int i = 0; i < moves.length; i++) {
				g.drawString(moves[i].get_name() + ": " + moves[i].get_desirability(battle.getEnemy(), battle.getPlayer().getPokemon()), leftOffset + 260f, 100f + (i * 15));
			}
			g.drawRect(leftOffset - 5f, 82f, 200f, 85f);
			g.drawString("Enemy stats: ", leftOffset, 85f);
			g.drawString("HP: "  + battle.getEnemy().get_health(), leftOffset, 100f);
			g.drawString("Water strength: " + battle.getEnemy().get_water_strength(), leftOffset, 115f);
			g.drawString("Earth strength: " + battle.getEnemy().get_earth_strength(), leftOffset, 130f);
			g.drawString("Fire strength: " + battle.getEnemy().get_fire_strength(), leftOffset, 145f);
		}
		
		if(battle.isOver() && !battle.hasPlayerWon()) { //player lost, dont show player
			g.drawString("You lost! Press enter to leave", leftOffset, 450f);
			if(battle.getPlayer().getInventory().hasItem(4)) {
				g.drawString("You should use the protein you have in your inventory!", leftOffset, 465f);
			} else {
				g.drawString("To heal your pokemon, visit the old lady at the beginning!", leftOffset, 465f);
			}
		} else {
			g.drawImage(playerImage, leftOffset + 260f, 400f);
			g.drawString("Your moves:", leftOffset + 260f, 475f);
			IMove[] myMoves = battle.getEnemy().get_moves();
			for(int i = 0; i < myMoves.length; i++) {
				if(i == selectedMove) 
					g.setColor(new Color(255, 255, 255, 255));
				else
					g.setColor(new Color(255, 255, 255, 128));
				g.drawString(myMoves[i].get_name() + ": " + myMoves[i].get_desirability(battle.getPlayer().getPokemon(), battle.getEnemy()), leftOffset + 260f, 490f + (i * 15));
			}
			g.setColor(new Color(255, 255, 255, 255));
			g.drawRect(leftOffset - 5f, 472f, 200f, 85f);
			g.drawString("Your stats: ", leftOffset, 475f);
			g.drawString("HP: "  + battle.getPlayer().getPokemon().get_health(), leftOffset, 490f);
			g.drawString("Water strength: " + battle.getPlayer().getPokemon().get_water_strength(), leftOffset, 505f);
			g.drawString("Earth strength: " + battle.getPlayer().getPokemon().get_earth_strength(), leftOffset, 520f);
			g.drawString("Fire strength: " + battle.getPlayer().getPokemon().get_fire_strength(), leftOffset, 535f);
			
			g.drawString("Battle log:", 500f, 20f);
			ArrayList<String> log = battle.getBattleLog();
			int startIndex = 0;
			if(log.size() > showCount) 
				startIndex = log.size() - showCount;
			
			for(int i = startIndex; i < startIndex + showCount && i < log.size(); i++) {
				g.drawString(log.get(i), 500f, 35f + ((i - startIndex) * 15));
			}
		}
		
		super.render(gc, arg1, g);
	}

	
	
}
