package org.bakkes.game.view;

import org.bakkes.game.AModule;
import org.bakkes.game.view.components.ITextableShape;
import org.bakkes.game.view.components.TextLine;

public class ViewModule extends AModule{

	@Override
	protected void configure() {
		bind(ITextableShape.class).to(TextLine.class);
	}
}
