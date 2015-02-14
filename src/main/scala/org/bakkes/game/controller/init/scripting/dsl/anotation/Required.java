package org.bakkes.game.controller.init.scripting.dsl.anotation;

/**
 * methods that HAVE to be called in the dsl to complete the object should be annotated with this
 *
 * TODO: use https://github.com/google/guice/wiki/AOP to automagicly fire a message when not all required methods have been called for a dsl
 */
public @interface Required {
}
