package org.bakkes.game.model.entity;

import groovy.lang.Closure;
import org.bakkes.game.model.IModel;

public interface IInteractable extends IModel {
    public void setInteract(final Closure<Void> callback);
    public void interact();
}
