package org.bakkes.game.scripting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.python.core.Py;
import org.python.core.PyException;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class ScriptManager {
	private static PythonInterpreter interpreter = new PythonInterpreter();
	
	public static <T> T getVariable(String variableName) {
		return (T)interpreter.get(variableName);
	}
	
	public static <T> T executeFunction(Class<T> type, String name, Object... binds) {
		try {
			PyObject obj = interpreter.get(name);
			if(obj != null && obj instanceof PyFunction) {
				PyFunction function = (PyFunction)obj;
				PyObject[] objects = Py.javas2pys(binds);
				Object result = function.__call__(objects).__tojava__(type);
				return type.cast(result);
			}
		} catch (PyException exception) {
			//script not implemented
		}
		return null;
	}
	
	public static boolean executeFunction(String name, Object... binds) {
		try {
			PyObject obj = interpreter.get(name);
			if(obj != null && obj instanceof PyFunction) {
				PyFunction function = (PyFunction)obj;
				PyObject[] objects = Py.javas2pys(binds);
				function.__call__(objects);
				return true;
			}
		} catch (PyException exception) {
			//script not implemented
		}
		return false;
	}
	
	public static void loadScripts() {
		interpreter.cleanup();
		loadRecursively(new File("./res/scripts/"));
		System.out.println("Scripts loaded.");
	}
	
	private static void loadRecursively(File dir) {
		if(dir.isDirectory()) {
			for(File child : dir.listFiles()) {
				loadRecursively(child);
			}
		} else {
			if(dir.getName().endsWith(".py")) {
				try {
					interpreter.execfile(new FileInputStream(dir));
					System.out.println("Loaded " + dir.getAbsolutePath());
				} catch (FileNotFoundException e) {
					System.out.println("Unable to load file: " + dir.getAbsolutePath());
					e.printStackTrace();
				}
			}
		}
	}
}
