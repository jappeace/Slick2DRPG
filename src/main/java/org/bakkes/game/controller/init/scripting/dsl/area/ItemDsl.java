package org.bakkes.game.controller.init.scripting.dsl.area;

import groovy.lang.Closure;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.controller.init.scripting.dsl.ADsl;
import org.bakkes.game.controller.init.scripting.loader.ItemLoader;
import org.bakkes.game.model.entity.player.invetory.Item;
import org.bakkes.game.model.map.Tile;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ItemDsl extends ADsl{

	private Collection<Item> result = new LinkedList<>();
	private @Inject Provider<InteractDsl> interactDsl; // interact dsl is stateless, but we just asume it wil become statefull
	private @Inject ItemLoader loader;

	public Item item(
        final String name,
        final int x,
        final int y,
        final Closure<Void> commands
    ){
		final Item subject = item(name,x,y);
		commands.setDelegate(interactDsl.get());
		subject.setInteract(commands);
		getResult().add(subject);
		return subject;
	}
	public Item item(
        final String name,
        final int locationX,
        final int locationY
    ){
		final Item subject = loader.load(name);
		subject.setPosition(new Tile(locationX, locationY).topLeftPixels());
		getResult().add(subject);
		return subject;
	}

	public Collection<Item> getResult() {
		return result;
	}
}
