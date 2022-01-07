package dataframe;

import visitor.DataframeVisitor;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface DataFrame {

    List<Map<String, Object>> getList();

    String at(int row, String label);

    String iat(int row, int column);

    int columns();

    int size();

    List<String> sort(String label, String comparator);

    List<Map<String, Object>> query(Predicate<Map<String, Object>> predicate);

    void accept(DataframeVisitor visitor);

}
