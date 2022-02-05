package scala.composite

import factory.{AbstractFactory, AbstractReader, CSVFactory}

import scala.collection.mutable.ListBuffer
import scala.dataframe.ScalaDataframe
import scala.jdk.CollectionConverters._
import scala.visitor.ScalaDataframeVisitor

class ScalaDataframeAPI(filePath:String) extends ScalaDataframe {

  val factoryCSV:AbstractFactory = new CSVFactory
  val csvReader:AbstractReader = factoryCSV.createReader()
  var list: ListBuffer[Map[String, AnyRef]] = csvReader.createReader(filePath).asScala.to(ListBuffer).map(_.asScala.toMap)

  /**
   * @return The list of data contained in the dataframe
   */
  override def getList: ListBuffer[Map[String, AnyRef]] = list

  /**
   * Returns the value of a single item and column label
   *
   * @param row   The item from the list
   * @param label The element we want the value
   * @return The value of the element
   */
  override def at(row: Int, label: String): String = {
    val map = list(row)
    map.get(label).toString
  }

  /**
   * @return The number of labels in the list
   */
  override def columns: Int = {
    val map = list.head
    map.size
  }

  /**
   * @return The number of items in the list
   */
  override def size: Int = list.size

  override def accept(visitor:ScalaDataframeVisitor): Unit = {
    visitor.visit(this)
  }
}
