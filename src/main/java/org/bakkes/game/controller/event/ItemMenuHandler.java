package org.bakkes.game.controller.event;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.R;
import org.bakkes.game.model.entity.player.invetory.Inventory;
import org.bakkes.game.model.entity.player.invetory.Item;
import org.bakkes.game.view.components.IShape;
import org.bakkes.game.view.overworld.SpritedNameView;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class ItemMenuHandler implements IMenuHandler {

	private @Inject @Named("from player") Inventory inventory;
	private @Inject Provider<SpritedNameView> viewsProvider;
	@Override
	public void select(final int item) {
	}

	@Override
	public Collection<IShape> getOptions() {
		final Collection<IShape> result = new LinkedList<>();
		for(final Item item : inventory){
			final SpritedNameView view = viewsProvider.get();
			view.setNamed(R.itemSprites, item);
			result.add(view);
		}
		return result;
	}

}
