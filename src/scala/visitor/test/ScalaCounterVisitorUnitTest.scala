package scala.visitor.test

import factory.{AbstractFactory, AbstractReader, CSVFactory}
import org.junit.jupiter.api.Test

import java.io.IOException
import scala.collection.mutable.ListBuffer
import scala.composite.{ScalaDataframeAPI, ScalaDirectoryDataframe}
import scala.jdk.CollectionConverters.{CollectionHasAsScala, MapHasAsScala}
import scala.visitor.ScalaCounterVisitor

class ScalaCounterVisitorUnitTest {

  val factoryCSV: AbstractFactory = new CSVFactory
  val csvReader: AbstractReader = factoryCSV.createReader()
  var df,df2,df3: ScalaDataframeAPI = _
  var ddf, ddf2: ScalaDirectoryDataframe = _
  var list: ListBuffer[Map[String, AnyRef]] = new ListBuffer[Map[String, AnyRef]]
  var v,v2,v3: ScalaCounterVisitor = _

  {
    try {
      df = new ScalaDataframeAPI(".\\src\\api\\APIFiles\\cities.csv")
      df2 = new ScalaDataframeAPI(".\\src\\composite\\EU\\Spain\\Catalonia.csv")
      df3 = new ScalaDataframeAPI(".\\src\\composite\\EU\\Spain\\Galicia.csv")
      ddf = new ScalaDirectoryDataframe(".\\src\\composite\\EU")
      ddf2 = new ScalaDirectoryDataframe(".\\src\\composite\\EU\\Spain")
      v = new ScalaCounterVisitor()
      v2 = new ScalaCounterVisitor()
      v3 = new ScalaCounterVisitor()
      ddf2.addChild(df2)
      ddf2.addChild(df3)
      ddf.addChild(ddf2)
    } catch {
      case e: IOException => e.printStackTrace()
    }

  }


  @Test
  def testCounterVisitor(): Unit ={
    df.accept(v)
    v.getStatus()
    ddf.accept(v2)
    v2.getStatus()
    ddf2.accept(v3)
    v3.getStatus()
  }
}
