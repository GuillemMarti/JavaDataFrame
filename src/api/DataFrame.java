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

    public String at (int item, String label) {
        Map<String, Object> map = new LinkedHashMap<>();
        map = list.get(item);
        return (map.get(label).toString());
    }
}
