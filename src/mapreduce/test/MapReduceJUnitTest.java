package mapreduce.test;

import api.DataFrameAPI;
import dataframe.DataFrame;
import mapreduce.MapReduce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        String sum = "384";
        System.out.println("Testing SumSizeList...");
        String sum2 = MapReduce.mapRed(list, x -> x.getList().get(0).get("LatD").equals(48.0), f -> (double) f.size(), Double::sum);
        Assertions.assertEquals(sum, sum2);
        System.out.println(sum + " | " + MapReduce.mapRed(list, x -> x.getList().get(0).get("LatD").equals(48.0), f -> (double) f.size(), Double::sum));
    }

    @Test
    public void testMinValue() {
        String min = "0";
        System.out.println("Testing MinValue...");
        String min2 = MapReduce.mapRed(list, x -> x.getList().get(0).get("LatD").equals(48.0), f -> (double) f.size(), Double::min);
        Assertions.assertEquals(min, min2);
        System.out.println(min + " | " + min2);

    }

    @Test
    public void testMaxColumnsList() {
        String max = "10";
        System.out.println("Testing MaxColumnsList...");
        Assertions.assertEquals(max, MapReduce.mapRed(list, x -> x.getList().get(0).get("LatD").equals(48.0), f -> (double) f.columns(), Double::max));
        System.out.println(max + " | " + MapReduce.mapRed(list,x -> x.getList().get(0).get("LatD").equals(48.0), f -> (double) f.columns(), Double::max));

    }

}
