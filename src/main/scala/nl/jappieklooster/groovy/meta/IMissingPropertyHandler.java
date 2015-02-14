package nl.jappieklooster.groovy.meta;

/**
 * if a groovy script does not find a porperty this method will be called
 * in favour of throwing an exception
 */
public interface IMissingPropertyHandler {
	public void propertyMissing(final String name);
}
