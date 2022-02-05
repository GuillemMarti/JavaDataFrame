package scala.visitor

import scala.collection.mutable.ListBuffer
import scala.composite.{ScalaDataframeAPI, ScalaDirectoryDataframe}

class ScalaFilterVisitor(label:String,value:Any, condition:Boolean) extends ScalaDataframeVisitor {

  var list: ListBuffer[Map[String, AnyRef]] = _

  def getStatus: ListBuffer[Map[String, AnyRef]] = {
    list
  }

  override def visit(scalaDataframeAPI: ScalaDataframeAPI): Unit = (condition) match {
    case "greater" => scalaDataframeAPI.getList.foreach(f => if (f.get(label).asInstanceOf[Double]>value.asInstanceOf[Double]) list.addOne(f))
    list = scalaDataframeAPI.getList
    list.filter(condition)

  }



  override def visit(scalaDataframeDirectory: ScalaDirectoryDataframe): Unit = {

  }

}
