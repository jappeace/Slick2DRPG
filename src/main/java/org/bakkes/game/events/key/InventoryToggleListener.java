package org.bakkes.game.events.key;

import org.bakkes.game.state.OverworldState;
import org.bakkes.game.view.InventoryGameComponent;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.util.Log;

public class InventoryToggleListener implements IKeyListener {
	private OverworldState game;
	private InventoryGameComponent inventoryComponent;
	private boolean enabled = false;

	public InventoryToggleListener(final OverworldState playingGameState, final InventoryGameComponent inventoryComponent) {
		this.game = playingGameState;
		this.inventoryComponent = inventoryComponent;
	}

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
