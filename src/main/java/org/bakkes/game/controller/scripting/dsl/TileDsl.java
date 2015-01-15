package org.bakkes.game.controller.scripting.dsl;

import java.util.LinkedList;
import java.util.List;

import org.bakkes.game.model.map.Tile;

import com.google.inject.Inject;
import com.google.inject.Provider;

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
