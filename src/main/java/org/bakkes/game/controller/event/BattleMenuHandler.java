package org.bakkes.game.controller.event;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.controller.state.battle.contestent.PlayerContestent;
import org.bakkes.game.model.battle.move.IMove;
import org.bakkes.game.model.pokemon.Pokemon;
import org.bakkes.game.view.components.IShape;
import org.bakkes.game.view.components.ITextableShape;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class BattleMenuHandler implements IMenuHandler{

    private @Inject @Named("current players") Provider<Pokemon> pokemon;
    private @Inject Provider<ITextableShape> lines;
    private @Inject PlayerContestent contestent;
	@Override
	public void select(final int item) {
		contestent.selectMove(item);
	}

	@Override
	public Collection<IShape> getOptions() {
        final Collection<IShape> result = new LinkedList<>();
        for(final IMove move : pokemon.get().getMoves()){
        	result.add(lines.get().setText(move.getName()));
        }
		return result;
	}

}
