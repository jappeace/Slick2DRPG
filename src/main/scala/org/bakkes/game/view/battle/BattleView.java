package org.bakkes.game.view.battle;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.bakkes.game.controller.state.battle.Battle;
import org.bakkes.game.controller.state.battle.contestent.PlayerContestent;
import org.bakkes.game.model.GameInfo;
import org.bakkes.game.model.map.Direction;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.AView;
import org.bakkes.game.view.SpriteType;
import org.bakkes.game.view.components.Menu;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BattleView extends AView{

	private @Inject Battle battle;
	private BattlePokeView enemyView;
	private BattlePokeView playerView;
	private Menu moves;
	private @Inject PlayerContestent playerContestent;
	@Inject
	public BattleView(
			final Provider<BattlePokeView> viewProvider,
			final @Named("moves") Menu moveMenu,
			@Named("current players") final Pokemon playerPoke,
			@Named("current enemys") final Pokemon enemyPoke
        ){
		enemyView = viewProvider.get();
		playerView = viewProvider.get();
		playerView.setPokemon(playerPoke, SpriteType.back, Direction.North);
		enemyView.setPokemon(enemyPoke, SpriteType.front, Direction.South);
		float padding = 20;
		enemyView.x(enemyView.avatar().width() *3+ padding);
		enemyView.y(padding);
		float height = playerView.statsView().height();
		playerView.y(GameInfo.SCREEN_HEIGHT-height-padding);
		playerView.x(padding);
		moves=moveMenu;
		moves.x(playerView.width());
		moves.y(GameInfo.SCREEN_HEIGHT - height);

	}
	@Override
	protected void renderView(final Graphics g) {
        g.setColor(new Color(255, 255, 255, 255));
		g.setLineWidth(5f);
		out.clear();
		out.x(20);
		out.y(150);

		if(battle.isOver() && playerContestent.hasWon()) { //player won, don't show enemy stuff
			out.write("You are victorious! Press enter to leave");
		} else {
			enemyView.render(g);
		}

		if(battle.isOver() && !playerContestent.hasWon()) { //player lost, dont show player
			out.y(out.y() + 300);
			out.write("You lost! Press enter to leave");
			out.write("To heal your pokemon, visit the old lady at the beginning!");
		} else {
			moves.render(g);
			playerView.render(g);
		}
		out.render(g);
	}

}
