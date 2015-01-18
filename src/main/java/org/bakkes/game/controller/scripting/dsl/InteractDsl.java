package org.bakkes.game.controller.scripting.dsl;

import org.bakkes.game.R;
import org.bakkes.game.controller.scripting.ScriptLoader;
import org.bakkes.game.model.entity.npc.Person;
import org.bakkes.game.model.entity.player.Player;
import org.bakkes.game.view.overworld.DialogBox;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class InteractDsl extends ADsl {
	private @Inject Provider<DialogBox> dialogProvider;
	private @Inject Player player;
	private @Inject Provider<ItemDsl> itemDslProvider;
	private @Inject ScriptLoader loader;

	public Person target;
	public DialogBox dialog( final String text){
		final DialogBox dialog = dialogProvider.get();
		dialog.setTitle(target.getName() + ":");
		dialog.setText(text);
		dialog.show();
		return dialog;
	}

	public void give(final String ... items){
		for(final String itemName : items){
			final ItemDsl dsl = itemDslProvider.get();
			loader.load(R.itemScripts + itemName + ".dsl", dsl);
			dsl.setItemName(itemName);
            player.getInventory().addItem(dsl.getItem());
		}
	}

	public void tought(final String text){
		final DialogBox box = dialog(text);
		box.setTitle("you think:");
	}
}
