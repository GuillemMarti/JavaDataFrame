package scala.visitor

import scala.composite.{ScalaDataframeAPI, ScalaDirectoryDataframe}
import scala.dataframe.ScalaDataframe

class ScalaCounterVisitor extends ScalaDataframeVisitor {

  var files = 0
  var dirs = 0

  def getStatus(): Unit = {
    println("DataFrame files: " + files + " DataFrame dirs: " + dirs)
  }

  /**
   * Visits a collection of dataframes and counts the number of dataframes and directory dataframes it has visited.
   *
   * @param scalaDataframe The dataframe or directory dataframe to visit
   */
  override def visit(scalaDataframe: ScalaDataframe): Unit = scalaDataframe match {
    case _:ScalaDataframeAPI => files += 1
    case b:ScalaDirectoryDataframe =>
      dirs += 1
      b.getChildren.foreach(df => df.accept(this))

  }
}
