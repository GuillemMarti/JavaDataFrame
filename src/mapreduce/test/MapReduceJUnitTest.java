package mapreduce.test;

import api.DataFrameAPI;
import dataframe.DataFrame;
import mapreduce.MapReduce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapReduceJUnitTest {

    DataFrameAPI df1, df2, df3;
    List<DataFrame> list = new ArrayList<>();

    {
        try {
            df1 = new DataFrameAPI(".\\src\\api\\APIFiles\\cities.csv","csv");
            df2 = new DataFrameAPI(".\\src\\api\\APIFiles\\cities.json","json");
            df3 = new DataFrameAPI(".\\src\\api\\APIFiles\\cities.txt","txt");
            list.add(df1);
            list.add(df2);
            list.add(df3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSumSizeList() {
        double sum = 384.0;
        System.out.println("Testing SumSizeList...");
        Assertions.assertEquals(sum, MapReduce.mapRed(list, f -> (double) f.size(), Double::sum));
        System.out.println(sum + " | " + MapReduce.mapRed(list, f -> (double) f.size(), Double::sum));
    }

    @Test
    public void testMinValue() {
        double min = 0.0;
        System.out.println("Testing MinValue...");
        Assertions.assertEquals(min, MapReduce.mapRed(list, f -> (double) f.size(), Double::min));
        System.out.println(min + " | " + MapReduce.mapRed(list, f -> (double) f.size(), Double::min));

    }

    @Test
    public void testMaxColumnsList() {
        double max = 10.0;
        System.out.println("Testing MaxColumnsList...");
        Assertions.assertEquals(max, MapReduce.mapRed(list, f -> (double) f.columns(), Double::max));
        System.out.println(max + " | " + MapReduce.mapRed(list, f -> (double) f.columns(), Double::max));

    }

}
