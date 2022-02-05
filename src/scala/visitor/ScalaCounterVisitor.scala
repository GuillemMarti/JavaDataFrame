package scala.visitor

import scala.composite.{ScalaDataframeAPI, ScalaDirectoryDataframe}
import scala.dataframe.ScalaDataframe

class ScalaCounterVisitor extends ScalaDataframeVisitor {

  var files = 0
  var dirs = 0

  def getStatus(): Unit = {
    println("DataFrame files: " + files + " DataFrame dirs: " + dirs)
  }

  override def visit(scalaDataframe: ScalaDataframe): Unit = scalaDataframe match {
    case _:ScalaDataframeAPI => files += 1
    case b:ScalaDirectoryDataframe =>
      dirs += 1
      b.getChildren.foreach(df => df.accept(this))

  }
}
