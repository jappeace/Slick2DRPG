package org.bakkes.game.controller.init.scripting.dsl.area;

import org.bakkes.game.controller.init.scripting.dsl.ADsl;
import org.newdawn.slick.util.Log;

public class WildDsl extends ADsl {

	public Object missingMethod(final String name, final Object args){
		Log.info(name);
		return new Object();
	}
}
