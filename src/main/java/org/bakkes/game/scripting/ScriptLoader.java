package org.bakkes.game.scripting;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.newdawn.slick.util.Log;

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
	public boolean load(final String path, final Object delagate){
		boolean success = false;
		final File file = new File(path);
		if(!file.isFile()){
			Log.warn("script file is not a file: " + path);
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
