package scala.recursion.test

import factory.{AbstractFactory, AbstractReader, CSVFactory}
import org.junit.Test

import java.io.IOException
import scala.collection.mutable.ListBuffer
import scala.composite.ScalaDataframeAPI
import scala.jdk.CollectionConverters.{CollectionHasAsScala, MapHasAsScala}

class ScalaRecursionUnitTest {
  val factoryCSV: AbstractFactory = new CSVFactory
  val csvReader: AbstractReader = factoryCSV.createReader()
  var df: ScalaDataframeAPI = _
  var list: ListBuffer[Map[String, AnyRef]] = new ListBuffer[Map[String, AnyRef]]


  {
    try {
      df = new ScalaDataframeAPI(".\\src\\api\\APIFiles\\cities.csv")
      list = csvReader.createReader(".\\src\\api\\APIFiles\\cities.csv").asScala.to(ListBuffer).map(_.asScala.toMap)
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }

  @Test
  def testGetColumn(): Unit = {
    val list = df.getColumn("LatD")
    list.foreach(println)
  }
}
