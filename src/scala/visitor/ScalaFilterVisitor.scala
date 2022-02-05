package scala.visitor

import scala.collection.mutable.ListBuffer
import scala.composite.{ScalaDataframeAPI, ScalaDirectoryDataframe}
import scala.dataframe.ScalaDataframe

class ScalaFilterVisitor(condition:Map[String, AnyRef] => Boolean) extends ScalaDataframeVisitor {

  private val list = ListBuffer[Map[String, AnyRef]]()

  def getStatus: ListBuffer[Map[String, AnyRef]] = {
    println("Filtered elements:")
    list
  }

  override def visit(scalaDataframe: ScalaDataframe): Unit = scalaDataframe match {
    case a:ScalaDataframeAPI => list.appendAll(a.getList.filter(condition))
    case b:ScalaDirectoryDataframe => b.getChildren.foreach(p=>p.accept(this))
  }

}
