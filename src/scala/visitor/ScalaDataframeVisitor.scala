package scala.visitor

import scala.dataframe.ScalaDataframe

trait ScalaDataframeVisitor {
  def visit(scalaDataframe: ScalaDataframe): Unit
}
