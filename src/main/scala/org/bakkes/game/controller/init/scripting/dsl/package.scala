package org.bakkes.game.controller.init.scripting

/**
 * #DSL's
 *
 * dsls instances will be handeling the users' function calls, therefore they
 * should take extra care in making as little as possible visible.
 *
 * a dsl should usualy receive easy to create object or primitives, for example
 * strings and arrays. then these should be converted to actual application models
 *
 * the dsl' should make sure to provide error message to the log when stuff fails
 * we assume the user has acces to these and thus can improve his code
 */
package object dsl {}
