package scala.composite.test

import factory.{AbstractFactory, AbstractReader, CSVFactory}
import org.junit.Test
import org.junit.jupiter.api.Assertions

import java.io.IOException
import scala.collection.mutable.ListBuffer
import scala.composite.ScalaDataframeAPI
import scala.jdk.CollectionConverters.{CollectionHasAsScala, MapHasAsScala}

class ScalaDataframeUnitTest {

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
  def testAt(): Unit = {
    println("\nTesting At...")
    Assertions.assertEquals(list(25)("LatD").toString.trim, df.at(25, "LatD"))
    println(list(25)("LatD").toString.trim + " | " + df.at(25, "LatD"))
    Assertions.assertEquals(list(30)("City").toString.trim, df.at(30, "City"))
    println(list(30)("City").toString.trim + " | " + df.at(30, "City"))
  }

  @Test
  def testColumns(): Unit ={
    println("\nTesting columns...")
    Assertions.assertEquals(list.head.size,df.columns)
    println(list.head.size+" | "+df.columns)
  }

  @Test
  def testSize(): Unit ={
    println("\nTesting size...")
    Assertions.assertEquals(list.size,df.size)
    println(list.size+" | "+df.size)
  }

}
