package visitor.test;

import api.DataFrameAPI;
import composite.DirectoryDataframe;
import factory.AbstractFactory;
import factory.AbstractReader;
import factory.CSVFactory;
import factory.JSONFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import visitor.AvgVisitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AvgVisitorJUnitTest {

    AbstractFactory factoryCSV = new CSVFactory();
    AbstractFactory factoryJSON = new JSONFactory();
    AbstractReader jsonReader = factoryJSON.createReader();
    AbstractReader csvReader = factoryCSV.createReader();
    DataFrameAPI df1, df2, df3;
    DirectoryDataframe directoryDataframe1, directoryDataframe2;
    List<Map<String, Object>> list, listAux, listAux2;
    AvgVisitor avgVisitor = new AvgVisitor();

    {
        try {
            df1 = new DataFrameAPI(csvReader.createReader(".\\src\\composite\\EU\\Spain\\Catalonia.csv"));
            df2 = new DataFrameAPI(csvReader.createReader(".\\src\\composite\\EU\\Spain\\Galicia.csv"));
            df3 = new DataFrameAPI(jsonReader.createReader(".\\src\\composite\\EU\\Germany.json"));
            directoryDataframe1 = new DirectoryDataframe(".\\src\\composite\\EU");
            directoryDataframe2 = new DirectoryDataframe(".\\src\\composite\\EU\\Spain");
            list = new ArrayList<>(jsonReader.createReader(".\\src\\composite\\EU\\Germany.json"));
            listAux = new ArrayList<>(csvReader.createReader(".\\src\\composite\\EU\\Spain\\Catalonia.csv"));
            listAux2 = new ArrayList<>(csvReader.createReader(".\\src\\composite\\EU\\Spain\\Galicia.csv"));
            list.addAll(listAux);
            list.addAll(listAux2);
            directoryDataframe1.addChild(df3);
            directoryDataframe1.addChild(directoryDataframe2);
            directoryDataframe2.addChild(df1);
            directoryDataframe2.addChild(df2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVisit() {
        double avg = 50.75;
        System.out.println("Testing AvgVisitor on single Dataframe...");
        df3.accept(avgVisitor, "LatD");
        Assertions.assertEquals(avg, avgVisitor.getStatus());
        System.out.println(avg + " | " + avgVisitor.getStatus());
        System.out.println("\nTesting AvgVisitor on directory Dataframe...");
        avg = 44.75;
        directoryDataframe1.accept(avgVisitor, "LatD");
        Assertions.assertEquals(avg, avgVisitor.getStatus());
        System.out.println(avg + " | " + avgVisitor.getStatus());
    }
}
