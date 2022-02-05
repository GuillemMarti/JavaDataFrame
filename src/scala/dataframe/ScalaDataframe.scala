package scala.dataframe

import scala.collection.mutable.ListBuffer
import scala.visitor.ScalaDataframeVisitor

trait ScalaDataframe {

  def getList: ListBuffer[Map[String, AnyRef]]

  def at(row: Int, label: String): String

  def columns: Int

  def size: Int

  def accept(visitor:ScalaDataframeVisitor): Unit
}
