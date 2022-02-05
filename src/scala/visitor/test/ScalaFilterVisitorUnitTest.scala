package scala.visitor.test

import factory.{AbstractFactory, AbstractReader, CSVFactory}
import org.junit.Test

import java.io.IOException
import scala.collection.mutable.ListBuffer
import scala.composite.ScalaDataframeAPI
import scala.jdk.CollectionConverters.{CollectionHasAsScala, MapHasAsScala}
import scala.visitor.ScalaFilterVisitor

class ScalaFilterVisitorUnitTest {

  val factoryCSV: AbstractFactory = new CSVFactory
  val csvReader: AbstractReader = factoryCSV.createReader()
  var df: ScalaDataframeAPI = _
  var list: ListBuffer[Map[String, AnyRef]] = new ListBuffer[Map[String, AnyRef]]
  var v,v2,v3: ScalaFilterVisitor = _




  {
    try {
      df = new ScalaDataframeAPI(".\\src\\api\\APIFiles\\cities.csv")
      list = csvReader.createReader(".\\src\\api\\APIFiles\\cities.csv").asScala.to(ListBuffer).map(_.asScala.toMap)
      v = new ScalaFilterVisitor("LatD",49.0,"greater")
     /* v2 = new ScalaFilterVisitor(df=>df.get("LatD").asInstanceOf[Double]<(49.0))
      v3 = new ScalaFilterVisitor(df=>df.get("City").asInstanceOf[String].equals("Regina"))*/

    } catch {
      case e: IOException => e.printStackTrace()
    }

  }


  @Test
  def testFilterVisitor(): Unit ={
    df.accept(v)
    println(v.getStatus)
  }
}
