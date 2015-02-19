package nl.jappieklooster.scala

import nl.jappieklooster.groovy.operator.IPlusOperator

trait TPlus[T] extends IPlusOperator[T] {
	def +(rhs:T) = plus(rhs)
}
