package org.bakkes.game.model;

import groovy.lang.Closure;

public interface IInteractable extends IModel{
    public void setInteract(final Closure<Void> callback);
    public void interact();
}
