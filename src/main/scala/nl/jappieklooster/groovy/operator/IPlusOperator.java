package nl.jappieklooster.groovy.operator;

/**
 * if a java class implements this interface it allows the class to perform
 * groovy operator overloading for the plus operator: T result = implementingObject + Tobject
 * @param <T>
 */
public interface IPlusOperator<T> {
	T plus(T righthandSide);
}
