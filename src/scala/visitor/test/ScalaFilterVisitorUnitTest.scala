package scala.visitor.test

import factory.{AbstractFactory, AbstractReader, CSVFactory}
import org.junit.Test

import java.io.IOException
import scala.collection.mutable.ListBuffer
import scala.composite.{ScalaDataframeAPI, ScalaDirectoryDataframe}
import scala.jdk.CollectionConverters.{CollectionHasAsScala, MapHasAsScala}
import scala.visitor.ScalaFilterVisitor

class ScalaFilterVisitorUnitTest {

  val factoryCSV: AbstractFactory = new CSVFactory
  val csvReader: AbstractReader = factoryCSV.createReader()
  var df,df2,df3: ScalaDataframeAPI = _
  var ddf: ScalaDirectoryDataframe = _
  var list: ListBuffer[Map[String, AnyRef]] = new ListBuffer[Map[String, AnyRef]]
  var v,v2,v3,v4: ScalaFilterVisitor = _


  {
    try {
      df = new ScalaDataframeAPI(".\\src\\api\\APIFiles\\cities.csv")
      df2 = new ScalaDataframeAPI(".\\src\\composite\\EU\\Spain\\Catalonia.csv")
      df3 = new ScalaDataframeAPI(".\\src\\composite\\EU\\Spain\\Galicia.csv")
      ddf = new ScalaDirectoryDataframe(".\\src\\composite\\EU\\Spain")
      list = csvReader.createReader(".\\src\\api\\APIFiles\\cities.csv").asScala.to(ListBuffer).map(_.asScala.toMap)
      v = new ScalaFilterVisitor(df=>df("LatD").asInstanceOf[Double] > 47.0)
      v2 = new ScalaFilterVisitor(df=>df("LatD").asInstanceOf[Double] < 31.0)
      v3 = new ScalaFilterVisitor(df=>df("City").asInstanceOf[String].equals("Lugo"))
      v4 = new ScalaFilterVisitor(df=>df("LatM").asInstanceOf[Double] > 31.0)
      ddf.addChild(df2)
      ddf.addChild(df3)
    } catch {
      case e: IOException => e.printStackTrace()
    }

  }


  @Test
  def testFilterVisitor(): Unit ={
    df.accept(v)
    v.getStatus.foreach{println}
    df.accept(v2)
    v2.getStatus.foreach{println}
    ddf.accept(v3)
    v3.getStatus.foreach{println}
    ddf.accept(v4)
    v4.getStatus.foreach{println}
  }
}
