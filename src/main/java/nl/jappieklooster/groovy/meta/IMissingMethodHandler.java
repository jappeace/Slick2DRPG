package nl.jappieklooster.groovy.meta;

/**
 * it took a bit of experimenting to find this prototype
 * if a missing method occurs in a groovy script this prototype will handle the result
 * in favour of just throwing an exception
 */
public interface IMissingMethodHandler {
	void methodMissing(final String name, final Object args);
}
