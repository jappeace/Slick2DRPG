package nl.jappieklooster.scala

import nl.jappieklooster.groovy.operator.IMultiplyOperator

trait TMultiply [T] extends IMultiplyOperator[T]{
	def *(rhs:T):T = multiply(rhs)
}
