package scala.composite

import factory.{AbstractFactory, AbstractReader, CSVFactory}

import scala.annotation.tailrec
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
    map(label).toString
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

  /**
   * Gets a column of data from a dataframe
   * @param label The column to retrieve
   * @tparam A Generic type [Double, String]
   * @return A list of data containing the column.
   */
  def getColumn[A](label: String): List[A] = {
    val listAux = ListBuffer[A]()
    list.foreach(e=>listAux.addOne(e(label).asInstanceOf[A]))
    listAux.toList
  }

  /**
   * Function that filters and operates with a list of data from a dataframe using Stack recursion
   * @param condition The condition the data has to pass
   * @param operation The operation applied to the data
   * @param collection The initial collection of data
   * @tparam A The generic type
   * @return Returns a list containing data that has passed the condition and has recieved the corresponding operation
   */
  def listFilterMapStack[A](condition:A => Boolean,operation:A=>A,collection:List[A]): List[A] = collection match {
    case Nil => Nil
    case x :: xs => if (condition(x)) operation(x) :: listFilterMapStack(condition,operation,xs) else listFilterMapStack(condition,operation,xs)
  }

  /**
   * Function that filters and operates with a list of data from a dataframe using Tail recursion
   * @param condition The condition the data has to pass
   * @param operation The operation applied to the data
   * @param collection The initial collection of data
   * @tparam A The generic type
   * @return Returns a list containing data that has passed the condition and has recieved the corresponding operation
   */
   def listFilterMapTail[A](condition:A => Boolean, operation:A=>A, collection:List[A], accum:List[A]): List[A] = collection match {
    case Nil => accum
    case x :: xs => if (condition(x)) listFilterMapTail(condition,operation,xs,accum.appended(operation(x))) else listFilterMapTail(condition,operation,xs,accum)
  }

  override def accept(visitor:ScalaDataframeVisitor): Unit = {
    visitor.visit(this)
  }
}
