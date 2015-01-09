package org.bakkes.game.scripting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.bakkes.game.R;
import org.newdawn.slick.util.Log;
import org.python.core.Py;
import org.python.core.PyException;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class ScriptManager {
	private static PythonInterpreter interpreter = new PythonInterpreter();

	public static PythonInterpreter getInterpreter() {
		return interpreter;
	}

	public static <T> T getVariable(final String variableName) {
		System.out.println("Retrieved python variable " + variableName);
		return (T)interpreter.get(variableName);
	}

	public static <T> T executeFunction(final Class<T> type, final String name, final Object... binds) {
		try {
			System.out.println("Executing function " + name + "(" + binds.toString() + ")");
			final PyObject obj = interpreter.get(name);
			if(obj != null && obj instanceof PyFunction) {
				final PyFunction function = (PyFunction)obj;
				final PyObject[] objects = Py.javas2pys(binds);
				final Object result = function.__call__(objects).__tojava__(type);
				return type.cast(result);
			}
		} catch (final PyException exception) {
			//script not implemented
		}
		return null;
	}

	public static boolean executeFunction(final String name, final Object... binds) {
		try {
			System.out.println("Executing function " + name + "(" + binds.toString() + ")");
			final PyObject obj = interpreter.get(name);
			if(obj != null && obj instanceof PyFunction) {
				final PyFunction function = (PyFunction)obj;
				final PyObject[] objects = Py.javas2pys(binds);
				function.__call__(objects);
				return true;
			}
		} catch (final PyException exception) {
			//script not implemented
		}
		return false;
	}

	public static void loadScripts() {
		interpreter.cleanup();
		final File file = new File(R.scripts);
		if(!file.exists()){
			Log.debug("current working path is: " + new File(".").getAbsolutePath());
			throw new RuntimeException("script folder not found at " + R.scripts);
		}
		loadRecursively(file);
		System.out.println("Scripts loaded.");
	}

	private static void loadRecursively(final File file) {
		if(file.isDirectory()) {
			for(final File child : file.listFiles()) {
				loadRecursively(child);
			}
			return;
		}
        if(file.getName().endsWith(".py")) {
            try {
                interpreter.execfile(new FileInputStream(file));
                System.out.println("Loaded " + file.getAbsolutePath());
            } catch (final FileNotFoundException e) {
                System.out.println("Unable to load file: " + file.getAbsolutePath());
                e.printStackTrace();
            }
        }else{
        	Log.warn("trying to load a non python file: " + file.getAbsolutePath());
        }
	}
}
