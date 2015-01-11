package org.bakkes.game.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bakkes.game.battle.Battle;
import org.bakkes.game.model.pokemon.IMove;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class BattleState extends CommonGameState {
	public static final int BATTLE_STATE_ID = 2;

	private Battle battle;
	private Image playerImage;
	private Image enemyImage;
	private int selectedMove = 0;
	private boolean firstRun = true;
	Random random = new Random();
	@Override
	public int getID() {
		return BATTLE_STATE_ID;
	}

	public void setBattle(final Battle b) {
		this.battle = b;
		final String player = b.getPlayerPokemon().getSpritePath();
		final String enemy = b.getEnemy().getSpritePath();
		try {

			playerImage = new Image(player);
			enemyImage = new Image(enemy);
		} catch (final SlickException e) {
			Log.error("could not find: \n " + player + " \n or  \n" + enemy);
			e.printStackTrace();
		}
		firstRun = true;
	}

	private void selectMove(final int selected) {
		final List<IMove> moves = battle.getPlayerPokemon().getMoves();
		if(selected >= moves.size())
			selectedMove = 0;
		else if(selected < 0)
			selectedMove = moves.size() - 1;
		else
			selectedMove = selected;
	}

	@Override
	public void init(final GameContainer gc, final StateBasedGame arg1)
			throws SlickException {
		super.init(gc, arg1);
		System.out.println("Got init");
	}

	@Override
	public void update(final GameContainer gc, final StateBasedGame arg1, final int delta)
			throws SlickException {
		if(firstRun) {
			gc.getInput().isKeyPressed(Input.KEY_ENTER);//reset ENTER key state
			firstRun = false;
		}
		if(battle.isOver()) {
			if(gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
				selectedMove = -1;
				arg1.enterState(OverworldState.PLAYING_STATE_ID);
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
		if(!gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			return;
		}
        battle.executeMove(battle.getPlayerPokemon().getMoves().get(selectedMove), true);

        if(battle.isOver()){
            return;
        }

        //now execute opponents move
        final List<IMove> enemyMoves = battle.getEnemy().getMoves();
        battle.executeMove(enemyMoves.get(random.nextInt(enemyMoves.size()-1)), false);

		super.update(gc, arg1, delta);
	}
	private float leftOffset = 20f;
	private int showCount = 30;
	@Override
	public void render(final GameContainer gc, final StateBasedGame arg1, final Graphics g)
			throws SlickException {
		g.setColor(new Color(255, 255, 255, 255));
		g.setLineWidth(5f);

		if(battle.isOver() && battle.hasPlayerWon()) { //player won, don't show enemy stuff
			g.drawString("You are victorious! Press enter to leave", leftOffset, 150f);
		} else {
			g.drawImage(enemyImage, leftOffset + 260f, 10f);
			g.drawString("Enemy moves:", leftOffset + 260f, 85f);
			final List<IMove> moves = battle.getEnemy().getMoves();
			for(int i = 0; i < moves.size(); i++) {
				g.drawString(moves.get(i).getName(), leftOffset + 260f, 100f + (i * 15));
			}
			g.drawRect(leftOffset - 5f, 82f, 200f, 85f);
			g.drawString("Enemy stats: ", leftOffset, 85f);
			g.drawString("HP: "  + battle.getEnemy().getHealth(), leftOffset, 100f);
		}

		if(battle.isOver() && !battle.hasPlayerWon()) { //player lost, dont show player
			g.drawString("You lost! Press enter to leave", leftOffset, 450f);
            g.drawString("To heal your pokemon, visit the old lady at the beginning!", leftOffset, 465f);
		} else {
			g.drawImage(playerImage, leftOffset + 260f, 400f);
			g.drawString("Your moves:", leftOffset + 260f, 475f);
			final List<IMove> myMoves = battle.getEnemy().getMoves();
			for(int i = 0; i < myMoves.size(); i++) {
				if(i == selectedMove)
					g.setColor(new Color(255, 255, 255, 255));
				else
					g.setColor(new Color(255, 255, 255, 128));
				g.drawString(myMoves.get(i).getName() , leftOffset + 260f, 490f + (i * 15));
			}
			g.setColor(new Color(255, 255, 255, 255));
			g.drawRect(leftOffset - 5f, 472f, 200f, 85f);
			g.drawString("Your stats: ", leftOffset, 475f);
			g.drawString("HP: "  + battle.getPlayerPokemon().getHealth(), leftOffset, 490f);

			g.drawRect(490f, 15f, 300, 500);

			g.drawString("Battle log:", 500f, 20f);
			final ArrayList<String> log = battle.getBattleLog();
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
