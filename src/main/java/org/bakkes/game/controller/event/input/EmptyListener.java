package org.bakkes.game.controller.event.input;

/**
 * to avoid nullpointers use this:
 *
 * 		class ... {
 * 			private IKeylistener listener = new EmptyKeylistener();
 * 		}
 *
 * now you can later sate the listener but be sure that by default you don't get nullpointers
 */
public class EmptyListener extends AKeyListener{
}
