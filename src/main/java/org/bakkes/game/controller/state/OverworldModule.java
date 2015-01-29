package org.bakkes.game.controller.state;

import java.util.Collection;
import java.util.LinkedList;

import org.bakkes.game.AModule;
import org.bakkes.game.R;
import org.bakkes.game.controller.input.IKeyListener;
import org.bakkes.game.controller.input.InteractionListener;
import org.bakkes.game.controller.input.MenuToggleListener;
import org.bakkes.game.controller.input.MovementListener;
import org.bakkes.game.model.Bean;
import org.bakkes.game.model.entity.EntityTracker;
import org.bakkes.game.model.entity.IOverworldEntity;
import org.bakkes.game.model.entity.npc.Person;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.model.entity.player.invetory.Item;
import org.bakkes.game.model.map.IAreaNameAcces;
import org.bakkes.game.model.map.Tile;
import org.bakkes.game.view.IRenderable;
import org.bakkes.game.view.overworld.BlockedTileView;
import org.bakkes.game.view.overworld.CharacterView;
import org.bakkes.game.view.overworld.EntityView;
import org.bakkes.game.view.overworld.dialog.IMessageBox;
import org.bakkes.game.view.overworld.dialog.MessageBox;

import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

/**
 * TODO: this module should probably not be part of the main initilisation squence but one tier lower
 */
public class OverworldModule extends AModule{

	@Override
	public void configure(){
		// wtf, if it works.. This is not guices' fault, java has a horrible generic implementation
		// at compile time the generic flags are stripped away (after the checks) and replaced by
		// object, this means at runtime List<String> = List<Integer>, guice fixes this with type literals
		bind(new TypeLiteral<Bean<Tile>>(){}).in(Singleton.class);
		bind(new TypeLiteral<Bean<Integer>>(){}).in(Singleton.class);
		bind(IMessageBox.class).to(MessageBox.class);
	}
	private String lastArea = "";
	private Collection<IRenderable> renderable;

	/*
	 * its good that I don't have to call these methods
	 * Basically guice fills all the parameters
	 */
	public @Provides Collection<IRenderable> provideTranslatedRenderablas(
        final LinkedList<IRenderable> result,
        final EntityTracker<Person> personTracker,
        final EntityTracker<Item> itemTracker,
        final Provider<CharacterView> characterViewProvider,
        final Provider<EntityView> entityViewProvider,
        final Player player,
        final BlockedTileView blockedTiles,
        final IAreaNameAcces map
    ){
		if(lastArea.equals(map.getAreaName())){
			return renderable;
		}
		lastArea = map.getAreaName();
		renderable = result;
		for(final Person person : personTracker.getEntities()){
			final CharacterView view = characterViewProvider.get();
			view.setEntity(person);
			result.add(view);
		}
		for(final Item item : itemTracker.getEntities()){
			result.add(
                entityViewProvider.get().loadView(
                    R.itemSprites,
                    item
                )
            );
		}
        final CharacterView view = characterViewProvider.get();
        view.setEntity(player);
		result.add(view);
		result.add(blockedTiles);
		return result;
	}
	/*
	 * TODO:
	 * cache based on area name
	 */
	public @Provides Collection<IOverworldEntity> provideEntities(
        final LinkedList<IOverworldEntity> result,
        final EntityTracker<Person> personTracker,
        final EntityTracker<Item> itemTracker
    ){
		result.addAll(personTracker.getEntities());
		result.addAll(itemTracker.getEntities());
		return result;
	}
	@Provides Collection<IKeyListener> provideKeyListeners(
        final LinkedList<IKeyListener> linkedList,
        final InteractionListener npc,
        final MovementListener movement,
        final MenuToggleListener inventory
    ){

		linkedList.add(npc);
		linkedList.add(movement);
		linkedList.add(inventory);
		return linkedList;
	}
}
