package nl.jappieklooster.groovy.meta;

/**
 * it took a bit of experimenting to find this prototype
 * if a missing method occurs in a groovy script this prototype will handle the result
 * in favour of just throwing an exception
 */
public interface IMissingMethodHandler {
	/**
	 * @param name of the method that is missing
	 * @param args, is actualy an Object[] with args,
	 * but groovy only accepts it like this, so you need to cast it to Object[]
	 */
	void methodMissing(final String name, final Object args);
}
