package org.bakkes.game.model.font;

import org.bakkes.game.AModule;
import org.newdawn.slick.Font;

public class FontModule extends AModule{
	@Override
	public void configure(){
		/**
		 * mutable font also contains proper details
		 */
		bind(Font.class).to(MutableFont.class);
	}
}