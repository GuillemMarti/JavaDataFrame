package scala.visitor

import scala.composite.{ScalaDataframeAPI, ScalaDirectoryDataframe}

trait ScalaDataframeVisitor {
  def visit(scalaDataframeAPI: ScalaDataframeAPI): Unit
  def visit(scalaDataframeDirectory:ScalaDirectoryDataframe): Unit
}
