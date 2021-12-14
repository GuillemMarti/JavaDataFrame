package api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
}
