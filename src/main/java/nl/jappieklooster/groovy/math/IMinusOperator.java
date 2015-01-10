package nl.jappieklooster.groovy.math;

/**
 * if a java class implements this interface it allows the class to perform
 * groovy operator overloading for the minus operator: T result = implementingObject - Tobject
 * @param <T>
 */
public interface IMinusOperator<T> {
	T minus(T rightHand);
}
