package composite;

import api.DataFrameAPI;
import dataframe.DataFrame;

import java.util.*;

public class DirectoryDataframe implements DataFrame {

    String directoryName;
    List<DataFrameAPI> children;
    List<Map<String, Object>> list;

    public DirectoryDataframe(String directoryName){
        this.directoryName = directoryName;
        children = new LinkedList<>();
        list = new ArrayList<>();
    }

    public void addChild(DataFrameAPI child){
        children.add(child);
    }

    public void removeChild(DataFrameAPI child){
        children.remove(child);
    }


    @Override
    public List<Map<String, Object>> getList() {
        return list;
    }

    @Override
    public String at(int row, String label) {
        Map<String, Object> map;
        for (DataFrame child: children){
            map = child.getList().get(row);
            if (map.get(label) != null) {
                return map.get(label).toString().trim();
            }
        }
        return null;
    }

    @Override
    public String iat(int row, int column) {
        Map<String, Object> map;
        for (DataFrame child: children){
            map = child.getList().get(row);
            String key = map.keySet().toArray()[column].toString();
            if (map.get(key) != null) {
                return map.get(key).toString().trim();
            }
        }
        return null;
    }

    @Override
    public int columns() {
        int columns = 0;
        for (DataFrame child: children){
            columns = columns + child.columns();
        }
        return 0;
    }

    @Override
    public int size() {
        int size = 0;
        for(DataFrame child: children){
            size = size + child.size();
        }
        return 0;
    }

    @Override
    public List<String> sort(String label, String comparator) {
        List<String> list1 = new ArrayList<>();
        List<Map<String, Object>> childList;

        for(DataFrame child: children){
            childList = child.getList();
            for (var map : childList) {
                list1.add(map.get(label).toString().trim());
            }
        }

        switch (comparator) {
            case "ascending" -> list1.sort(Comparator.naturalOrder());
            case "descending" -> list1.sort(Comparator.reverseOrder());
        }

        return list1;
    }
}
