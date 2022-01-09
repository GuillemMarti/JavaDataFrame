package mapreduce;

import api.DataFrameAPI;
import dataframe.DataFrame;
import visitor.AvgVisitor;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapReduce {

    public static double mapRed (DataFrame df, Function<Map<String, Object>, Double> function, BinaryOperator<Double> op) {
        // Map
        List<Double> list = df.getList().stream().map(function).collect(Collectors.toList());

        //Reduce
        return list.stream().reduce(0.0, op);
    }
}

class Pruebas {
    public static void main(String[] args) throws IOException {
        DataFrameAPI df = new DataFrameAPI(".\\src\\api\\APIFiles\\cities.json","json");
        AvgVisitor avgVisitor = new AvgVisitor("LatD");

        Double i = MapReduce.mapRed(df, f -> (Double) f.get("LatD"), Double::min);
        System.out.println(i);
    }
}
