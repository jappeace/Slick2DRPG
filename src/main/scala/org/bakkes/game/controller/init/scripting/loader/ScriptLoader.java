package org.bakkes.game.controller.init.scripting.loader;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.newdawn.slick.util.Log;

/**
 * generic script loader. abstracts away the anucances of script loading
 *
 * if you load the same dsl on different locations you should make a specilized instance in this package and
 * inject that
 */
public class ScriptLoader {

	private GroovyShell shell;
	public ScriptLoader(){
        final CompilerConfiguration compilerConfig = new CompilerConfiguration();
        compilerConfig.setScriptBaseClass(DelegatingScript.class.getName());
        shell = new GroovyShell(new Binding(),compilerConfig);
	}

	/**
	 * handles the little pains of loading a groovy script
	 * may fail misserably silently
	 * @param path
	 * @param delagate this object will handle the dsl calls
	 * @return succes
	 */
	public boolean load(final Path path, final IScriptLoadableType delagate){
		Log.info("loading script: " + path);
		boolean success = false;
		final File file = path.toFile();
		if(!file.isFile()){
			Log.warn("script file is not a file: " + path + " \n pwd: " + new File(".").getAbsolutePath());
			return success;
		}

		try {
			final DelegatingScript script = (DelegatingScript)shell.parse(file);
            script.setDelegate(delagate);
            script.run();
            success = true;
		} catch (final CompilationFailedException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return success;
	}
}
