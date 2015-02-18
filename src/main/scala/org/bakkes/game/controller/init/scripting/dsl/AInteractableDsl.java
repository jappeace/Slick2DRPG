package org.bakkes.game.controller.init.scripting.dsl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import groovy.lang.Closure;
import org.bakkes.game.controller.init.scripting.dsl.area.InteractDsl;
import org.bakkes.game.model.IInteractable;

public abstract class AInteractableDsl extends ASpriteNamedDsl{
	private @Inject Provider<InteractDsl> dslProvider;

	public void onInteract(final Closure<Void> callback){
		final InteractDsl dsl = dslProvider.get();
		dsl.target = getInteractTarget();
		callback.setDelegate(dsl);
		getInteractTarget().setInteract(callback);
	}

	protected abstract IInteractable getInteractTarget();
}
