package dataframe;

import java.util.List;
import java.util.Map;

public interface DataFrame {


    List<Map<String, Object>> getList();
    String at(int row, String label);
    String iat(int row, int column);
    int columns();
    int size();
    List<String> sort(String label, String comparator);

}
