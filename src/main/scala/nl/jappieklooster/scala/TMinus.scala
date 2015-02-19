package nl.jappieklooster.scala

import nl.jappieklooster.groovy.operator.IMinusOperator

/**
 * allows both groovy and scala operator overloading.
 * @tparam T
 */
trait TMinus[T] extends IMinusOperator[T]{
	def -(rhs:T):T = minus(rhs)
}
