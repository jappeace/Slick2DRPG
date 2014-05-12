package org.bakkes.game.events;

import org.bakkes.game.Game;
import org.bakkes.game.scripting.ScriptManager;
import org.bakkes.game.ui.InventoryGameComponent;

public class InventoryToggleListener implements GameKeyListener {
	private Game game;
	private InventoryGameComponent inventoryComponent;
	private boolean enabled = false;
	
	public InventoryToggleListener(Game game, InventoryGameComponent inventoryComponent) {
		this.game = game;
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
