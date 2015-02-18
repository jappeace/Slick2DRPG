package org.bakkes.game.model.entity.player;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.bakkes.game.AModule;
import org.bakkes.game.model.entity.Character;
import org.bakkes.game.model.entity.player.invetory.Inventory;
import org.bakkes.game.model.entity.player.invetory.PokeBelt;

public class PlayerModule extends AModule{
	@Override
	protected void configure() {
		bind(Character.class).to(Player.class);
		bind(Player.class).in(Singleton.class);
	}
	@Provides
	@Named("from player")
	public  PokeBelt providePlayerBelt(final Player player){
		return player.getPokebelt();
	}
	@Provides
	@Named("from player")
	public Inventory providePlayerInventory(final Player player){
		return player.getInventory();
	}
}
