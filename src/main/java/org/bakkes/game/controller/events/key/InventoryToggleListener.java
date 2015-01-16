package org.bakkes.game.controller.events.key;

import org.bakkes.game.controller.state.OverworldState;
import org.bakkes.game.view.overworld.InventoryView;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.util.Log;

import com.google.inject.Inject;

public class InventoryToggleListener implements IKeyListener {
	private @Inject OverworldState game;
	private @Inject InventoryView inventoryComponent;
	private boolean enabled = false;

	@Override
	public void KeyDown(final int key, final char c) {
		if(key == 1) { //ESC
			enabled = !enabled;
			if(enabled) {
				game.addComponent(inventoryComponent);
			} else {
				game.removeComponent(inventoryComponent);
			}
		}
		if(enabled) {
			if(key == Keyboard.KEY_UP) {
				inventoryComponent.up();
			} else if(key == Keyboard.KEY_DOWN) {
				inventoryComponent.down();
			} else if(key == Keyboard.KEY_RETURN) {
				final int selectedSlot = inventoryComponent.getCurrentlySelected();
				Log.info("selected item: " + selectedSlot);
			}
		}

	}

	@Override
	public void KeyUp(final int key, final char c) {
		// TODO Auto-generated method stub

	}

}
