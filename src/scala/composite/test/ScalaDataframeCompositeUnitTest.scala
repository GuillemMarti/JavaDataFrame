package scala.composite.test

import factory.{AbstractFactory, AbstractReader, CSVFactory}
import org.junit.Test
import org.junit.jupiter.api.Assertions

import java.io.IOException
import scala.collection.mutable.ListBuffer
import scala.composite.{ScalaDataframeAPI, ScalaDirectoryDataframe}
import scala.jdk.CollectionConverters.{CollectionHasAsScala, MapHasAsScala}

class ScalaDataframeCompositeUnitTest {

  val factoryCSV: AbstractFactory = new CSVFactory
  val csvReader: AbstractReader = factoryCSV.createReader()
  var df,df2: ScalaDataframeAPI = _
  var directoryDataframe: ScalaDirectoryDataframe = _
  var directoryDataframe2: ScalaDirectoryDataframe = _
  var list,list2: ListBuffer[Map[String, AnyRef]] = new ListBuffer[Map[String, AnyRef]]

  {
    try {
      df = new ScalaDataframeAPI(".\\src\\composite\\EU\\Spain\\Catalonia.csv")
      df2= new ScalaDataframeAPI(".\\src\\composite\\EU\\Spain\\Galicia.csv")
      directoryDataframe = new ScalaDirectoryDataframe(".\\src\\composite\\EU")
      directoryDataframe2 = new ScalaDirectoryDataframe(".\\src\\composite\\EU\\Spain")
      list = csvReader.createReader(".\\src\\composite\\EU\\Spain\\Catalonia.csv").asScala.to(ListBuffer).map(_.asScala.toMap)
      list2 = csvReader.createReader(".\\src\\composite\\EU\\Spain\\Galicia.csv").asScala.to(ListBuffer).map(_.asScala.toMap)
      directoryDataframe2.addChild(df)
      directoryDataframe2.addChild(df2)
      directoryDataframe.addChild(directoryDataframe2)
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }

  @Test
  def testAt(): Unit = {
    println("\nTesting directory At...")
    Assertions.assertEquals(list(2)("LatS").toString.trim, directoryDataframe.at(2, "LatS"))
    println(list(2)("LatS").toString.trim + " | " + directoryDataframe.at(2, "LatS"))
    Assertions.assertEquals(list2(2)("City").toString.trim, directoryDataframe.at(6, "City"))
    println(list2(2)("City").toString.trim + " | " + directoryDataframe.at(6, "City"))
  }

  @Test
  def testColumns(): Unit ={
    println("\nTesting directory columns...")
    Assertions.assertEquals(list.head.size,directoryDataframe.columns)
    println(list.head.size+" | "+directoryDataframe.columns)
  }

  @Test
  def testSize(): Unit ={
    println("\nTesting directory size...")
    Assertions.assertEquals(list.size+list2.size,directoryDataframe.size)
    println(list.size+list2.size+" | "+directoryDataframe.size)
  }

}
