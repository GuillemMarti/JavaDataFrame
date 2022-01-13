package mapreduce;

import dataframe.DataFrame;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class MapReduce {

    private static final Object T = 0;

    /**
     * Returns a value following the MapReduce computing design pattern of a list of Dataframes
     *
     * @param df        List of Dataframes
     * @param predicate The condition we want to search in the DataFrame
     * @param function  Function for mapping
     * @param op        Operator for reduce
     * @return  The added value of computing, after applying the reduction
     */
    public static <T> String mapRed (List<DataFrame> df, Predicate<DataFrame> predicate, Function<DataFrame, T> function, BinaryOperator<T> op) {
        return df.stream().filter(predicate).map(function).reduce((T) T, op).toString();
    }
}
