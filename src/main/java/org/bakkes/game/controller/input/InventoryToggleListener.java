package org.bakkes.game.controller.input;

import org.bakkes.game.controller.state.OverworldState;
import org.bakkes.game.view.overworld.InventoryView;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class InventoryToggleListener extends AKeyListener{
	/*
	 * TODO: remove provider and make more sensible (overworld is a singleton)
	 */
	private @Inject Provider<OverworldState> game;
	private @Inject InventoryView inventoryComponent;
	private boolean enabled = false;

	@Override
	public void KeyDown(final Key key) {
		if(key.isMenu()) {
			enabled = !enabled;
			if(enabled) {
				game.get().addComponent(inventoryComponent);
			} else {
				game.get().removeComponent(inventoryComponent);
			}
		}
		if(enabled) {
			if(key.isUp()) {
				inventoryComponent.up();
			} else if(key.isDown()) {
				inventoryComponent.down();
			} else if(key.isConfirm()) {
				final int selectedSlot = inventoryComponent.getCurrentlySelected();
				Log.info("selected item: " + selectedSlot);
			}
		}

	}
}
