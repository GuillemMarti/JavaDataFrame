package api;

import java.util.*;
import java.util.stream.Collectors;

public class DataFrame {

    List<Map<String, Object>> list;

    public DataFrame() {
        this.list = new ArrayList<>();
    }

    public DataFrame(List<Map<String, Object>> list){
        this.list = list;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public String at (int row, String label) {
        Map<String, Object> map = list.get(row);
        return (map.get(label).toString().trim());
    }

    public String iat(int row, int column) {
        Map<String, Object> map = list.get(row);
        String key = map.keySet().toArray()[column].toString();
        return (map.get(key).toString().trim());
    }

    public int columns() {
        Map<String, Object> map = list.get(0);
        return map.size();
    }

    public int size() {
        return list.size()-1;
    }

    /**
     * Hacer factory con List<String> y List<Integer> y aplicar el comparator que toca
     */
    public List<String> sort(String label) {
        List<String> list1 = new ArrayList<>();
        for (var map : list ){
            list1.add(map.get(label).toString().trim());
        }

        return list1;
    }
}

class AlphabeticComparator implements Comparator<String> {
    public int compare(String a, String b){
        return(a.compareTo(b));
    }
}

class IntegerComparator implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        return Integer.compare(b, a);
    }
}
