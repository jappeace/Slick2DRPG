package org.bakkes.game.controller.event.input;

import java.util.Collection;

import com.google.inject.Inject;

public class CompositeKeyListener implements IKeyListener{

	private @Inject Collection<IKeyListener> keyListeners;
	@Override
	public void KeyDown(final Key key) {
		keyListeners.forEach(k -> k.KeyDown(key));
	}

	@Override
	public void KeyUp(final Key key) {
		keyListeners.forEach(k -> k.KeyUp(key));
	}

	public void clear(){
		this.keyListeners.clear();
	}
	public boolean add(final IKeyListener listner){
		return this.keyListeners.add(listner);
	}
}
