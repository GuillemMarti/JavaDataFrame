package visitor.test;

import api.DataFrameAPI;
import composite.DirectoryDataframe;
import factory.AbstractFactory;
import factory.AbstractReader;
import factory.CSVFactory;
import factory.JSONFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import visitor.MinVisitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MinVisitorJUnitTest {

    AbstractFactory factoryCSV = new CSVFactory();
    AbstractFactory factoryJSON = new JSONFactory();
    AbstractReader jsonReader = factoryJSON.createReader();
    AbstractReader csvReader = factoryCSV.createReader();
    DataFrameAPI df1, df2, df3;
    DirectoryDataframe directoryDataframe1, directoryDataframe2;
    List<Map<String, Object>> list, listAux, listAux2;
    MinVisitor minVisitor = new MinVisitor("LonD");

    {
        try {
            df1 = new DataFrameAPI(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv");
            df2 = new DataFrameAPI(".\\src\\composite\\EU\\Spain\\Galicia.csv","csv");
            df3 = new DataFrameAPI(".\\src\\composite\\EU\\Germany.json","json");
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
        System.out.println("Testing MinVisitor on single Dataframe...");
        df2.accept(minVisitor);
        Assertions.assertEquals(listAux2.get(2), minVisitor.getStatus());
        System.out.println(listAux2.get(2).toString().trim() + " | " + minVisitor.getStatus().toString().trim());
        System.out.println("\nTesting MinVisitor on directory Dataframe...");
        directoryDataframe1.accept(minVisitor);
        Assertions.assertEquals(list.get(6), minVisitor.getStatus());
        System.out.println(list.get(6).toString().trim() + " | " + minVisitor.getStatus().toString().trim());
    }
}
