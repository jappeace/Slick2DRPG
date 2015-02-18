package org.bakkes.game.controller.init.scripting.dsl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.bakkes.game.model.map.Tile;

import java.util.LinkedList;
import java.util.List;

public class TileDsl {

	private List<Tile> result = new LinkedList<>();
	private @Inject Provider<Tile> tileProvider;

	public void tile(final Integer left, final Integer top){
		final Tile created = tileProvider.get();
		created.left = left;
		created.top = top;
		getResult().add(created);
	}

	public List<Tile> getResult() {
		return result;
	}
}
