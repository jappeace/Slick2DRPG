package org.bakkes.game.controller.init.scripting.dsl;

import org.bakkes.game.model.map.Tile;

import java.util.LinkedList;
import java.util.List;

public class TileDsl {

	private List<Tile> result = new LinkedList<>();

	public void tile(final Integer left, final Integer top){
		getResult().add(new Tile(left, top));
	}

	public List<Tile> getResult() {
		return result;
	}
}
