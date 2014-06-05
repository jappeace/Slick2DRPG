package org.bakkes.game.events;

import org.bakkes.game.scripting.ScriptManager;
import org.bakkes.game.state.PlayingGameState;
import org.bakkes.game.ui.InventoryGameComponent;

public class InventoryToggleListener implements GameKeyListener {
	private PlayingGameState game;
	private InventoryGameComponent inventoryComponent;
	private boolean enabled = false;
	
	public InventoryToggleListener(PlayingGameState playingGameState, InventoryGameComponent inventoryComponent) {
		this.game = playingGameState;
		this.inventoryComponent = inventoryComponent;
	}
	
	public void KeyDown(int key, char c) {
		if(key == 1) { //ESC
			enabled = !enabled;
			if(enabled) {
				game.addComponent(inventoryComponent);
			} else {
				game.removeComponent(inventoryComponent);
			}
		}
		if(enabled) {
			if(key == 208) { //up
				inventoryComponent.up();
			} else if(key == 200) { //down
				inventoryComponent.down();
			} else if(key == 28) { //enter
				int selectedSlot = inventoryComponent.getCurrentlySelected();
				ScriptManager.executeFunction("inventorySelected", selectedSlot, game.getPlayer().getInventory().getItem(selectedSlot), game);
			}
		}
		
	}

	public void KeyUp(int key, char c) {
		// TODO Auto-generated method stub
		
	}

}
