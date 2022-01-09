package mapreduce;

import dataframe.DataFrame;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapReduce {

    /**
     * Returns a value following the MapReduce computing design pattern of a list of Dataframes
     *
     * @param df        List of Dataframes
     * @param function  Function for mapping
     * @param op        Operator for reduce
     * @return  The added value of computing, after applying the reduction
     */
    public static double mapRed (List<DataFrame> df, Function<DataFrame, Double> function, BinaryOperator<Double> op) {
        // Map
        List<Double> list = df.stream().map(function).collect(Collectors.toList());

        //Reduce
        return list.stream().reduce(0.0, op);
    }
}
