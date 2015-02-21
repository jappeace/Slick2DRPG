package org.bakkes.game.controller.state.battle.contestent;

import com.google.inject.Inject;
import org.bakkes.game.model.battle.move.IMove;
import scala.collection.Seq;

import java.util.Random;


public class RandyRandom extends AI{

	@Inject Random random;
	@Override
	protected IMove getMove() {
		final Seq<IMove> own = getOwnPokemon().getMoves();
		return own.take(random.nextInt(own.length())).last();
	}

}
