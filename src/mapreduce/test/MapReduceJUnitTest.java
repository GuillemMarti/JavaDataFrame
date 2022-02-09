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

    List<DataFrame> list = new ArrayList<>();
    DataFrameAPI df1,df2,df3;

    { try {
        df1 = new DataFrameAPI(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv");
        df2 = new DataFrameAPI(".\\src\\composite\\EU\\Spain\\Galicia.csv","csv");
        df3 = new DataFrameAPI(".\\src\\composite\\EU\\Germany.json","json");

        list.add(df1);
        list.add(df2);
        list.add(df3);

    } catch (IOException e) { e.printStackTrace(); }
    }

    @Test
    public void testSumSizeList() {
        String sum = "12";
        System.out.println("Testing SumSizeList...");
        String sum2 = MapReduce.mapRed(list, x -> x.size()>3, DataFrame::size, Integer::sum);
        Assertions.assertEquals(sum, sum2);
        System.out.println(sum + " | " + MapReduce.mapRed(list, x -> x.size()>3, DataFrame::size, Integer::sum));
    }

    @Test
    public void testMinValue() {
        String min = "4";
        System.out.println("Testing MinValue...");
        String min2 = MapReduce.mapRed(list, x -> x.size()>3, DataFrame::size, Integer::min);
        Assertions.assertEquals(min, min2);
        System.out.println(min + " | " + min2);

    }

    @Test
    public void testMaxColumnsList() {
        String max = "10";
        System.out.println("Testing MaxColumnsList...");
        Assertions.assertEquals(max, MapReduce.mapRed(list, x -> x.columns()>9, DataFrame::columns, Integer::max));
        System.out.println(max + " | " + MapReduce.mapRed(list,x -> x.columns()>9, DataFrame::columns, Integer::max));

    }

    @Test
    public void testCity() {
        String city = "BarcelonaSantiago de CompostelaHamburg";
        System.out.println("Testing City...");
        Assertions.assertEquals(city, MapReduce.mapRed(list, x -> x.columns()>9, f -> f.at(0, "City"), String::concat));
        System.out.println(city + " | " + MapReduce.mapRed(list, x -> x.columns()>9, f -> f.at(0, "City"), String::concat));
    }

}
