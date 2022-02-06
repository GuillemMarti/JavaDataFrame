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
    val list = df.getColumn[Double]("LatD")
    list.foreach(println)
    println()
    val list2 = df.getColumn[String]("City")
    list2.foreach(println)
  }

  @Test
  def testListFilterDouble(): Unit ={
    val list = df.getColumn[Double]("LatD")
    println("Filtered:")
    val list2 = df.listFilterMapStack[Double]((x:Double)=> x > 48.0,(y:Double)=> y+1000.0 ,list)
    list2.foreach(println)
    val list3 = df.listFilterMapTail[Double]((x:Double)=> x > 48.0,(y:Double)=> y+1000.0 ,list,List())
    println("Filtered:")
    list3.foreach(println)
  }

  @Test
  def testListFilterString(): Unit ={
    val list = df.getColumn[String]("City")
    println("Filtered:")
    val list2 = df.listFilterMapStack[String]((x:String)=> x.equals("Regina"),(y:String)=> y.concat("TOWN") ,list)
    list2.foreach(println)
    val list3 = df.listFilterMapTail[String]((x:String)=> x.equals("Watertown"),(y:String)=> y.concat("TOWN") ,list,List())
    println("Filtered:")
    list3.foreach(println)
  }
}
