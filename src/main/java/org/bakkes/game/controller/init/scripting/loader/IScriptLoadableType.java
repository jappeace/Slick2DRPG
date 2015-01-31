package org.bakkes.game.controller.init.scripting.loader;

/**
 * marker interface for stuff that can be put in the scriptloader
 *
 * type's implementing this interface should try to make things as least visible as possible,
 * since we want as little code as possible in the scripting (scripting is quite slow, also it quickly becomes hard to follow)
 */
public interface IScriptLoadableType {}

