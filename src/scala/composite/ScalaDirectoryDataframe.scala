package scala.composite

import scala.collection.mutable.ListBuffer
import scala.dataframe.ScalaDataframe
import scala.visitor.ScalaDataframeVisitor

class ScalaDirectoryDataframe(directoryName: String) extends ScalaDataframe {

  var children: ListBuffer[ScalaDataframe] = new ListBuffer[ScalaDataframe]

  /**
   * Adds a Dataframe to the list of dataframes in the directory
   *
   * @param child The file or directory to be added
   */
  def addChild(child: ScalaDataframe): Unit = {
    children += child
  }

  /**
   * Removes a Dataframe to the list of dataframes in the directory
   *
   * @param child The file or directory to be removed
   */
  def removeChild(child: ScalaDataframe): Unit = {
    children -= child
  }

  /**
   * Retreives the list of children assigned to the directory
   *
   * @return The list of subdirectories
   */
  def getChildren: ListBuffer[ScalaDataframe] ={
    children
  }

  /**
   * @return The list of data contained in the dataframe
   */
  override def getList: ListBuffer[Map[String, AnyRef]] = {
    val list: ListBuffer[Map[String, AnyRef]] = new ListBuffer[Map[String, AnyRef]]
    for (child <- children) {
      list.addAll(child.getList)
    }
    list
  }

  /**
   * Returns the value of a single item and column label, gets all the lists
   * of the files in the directory before searching
   *
   * @param row   The item from the list
   * @param label The element we want the value
   * @return The value of the element
   */
  override def at(row: Int, label: String): String = {
    val list = new ListBuffer[Map[String, AnyRef]]
    for (child <- children) {
      list.addAll(child.getList)
    }
    val map = list(row)
    if (map(label) != null)
      return map(label).toString.trim
    null
  }

  /**
   * @return The number of labels in the list
   */
  override def columns: Int = {
    var columns = 0
    for (child <- children) {
      columns += child.columns
    }
    columns / children.size
  }

  /**
   * @return The number of items all the lists in the directory
   */
  override def size: Int = {
    var size = 0
    for (child <- children) {
      size += child.size
    }
    size
  }

  override def accept(visitor: ScalaDataframeVisitor): Unit = {
    visitor.visit(this)
  }
}
