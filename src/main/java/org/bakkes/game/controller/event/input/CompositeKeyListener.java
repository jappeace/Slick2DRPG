package org.bakkes.game.controller.event.input;

import java.util.Collection;

import com.google.inject.Inject;

public class CompositeKeyListener implements IKeyListener{

	private @Inject Collection<IKeyListener> keyListeners;
	@Override
	public void KeyDown(final Key key) {
		for(final IKeyListener k : keyListeners){
			k.KeyDown(key);
		}
	}

	@Override
	public void KeyUp(final Key key) {
		for(final IKeyListener k : keyListeners){
			k.KeyUp(key);
		}
	}

	public void clear(){
		this.keyListeners.clear();
	}
	public boolean add(final IKeyListener listner){
		return this.keyListeners.add(listner);
	}
}
