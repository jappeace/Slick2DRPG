package org.bakkes.game.controller;

import org.bakkes.game.controller.state.event.input.IKeyListener;

/**
 * ## Controller
 * classes that detrmin the flow of the application should implement this interface
 *
 * ### why?
 * often the result of certain input events (key input) or changes in state of certain models
 * the application has to change its flow, (or keep doing what it has been doing).
 *
 * controllers are sort of dumb states, where states glue the rendering of views and the logic of
 * controllers together a controller is just logic
 *
 * seperating:
 *	* render stuff/views (often bulky but boring code), rendering is just a bunch of commands
 *		* views can often be reused and have lots of little configurations
 * 	* from logic (often precise where every little detail matters)
 * 		* logic is not really that reusable at all
 * is a best practice.
 */
public interface IController extends IKeyListener, IUpdatable{
}
