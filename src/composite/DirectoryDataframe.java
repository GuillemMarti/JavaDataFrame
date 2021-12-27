package composite;

import dataframe.DataFrame;

import java.util.*;

public class DirectoryDataframe implements DataFrame {

    String directoryName;
    List<DataFrame> children;


    public DirectoryDataframe(String directoryName){
        this.directoryName = directoryName;
        children = new LinkedList<>();
    }

    public void addChild(DataFrame child){
        children.add(child);
    }

    public void removeChild(DataFrame child){
        children.remove(child);
    }

    @Override
    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new LinkedList<>();
        for (DataFrame child: children){
            list.addAll(child.getList());
        }
        return list;
    }

    /**
     * Returns the value of a single item and column label, gets all the lists
     * of the files in the directory before searching
     *
     * @param row The item from the list
     * @param label The element we want the value
     * @return The value of the element
     */
    @Override
    public String at(int row, String label) {
        Map<String, Object> map;
        List<Map<String, Object>> list1 = new LinkedList<>();
        for (DataFrame child: children){
            list1.addAll(child.getList());
        }
        map = list1.get(row);
        if (map.get(label) != null) {
            return map.get(label).toString().trim();
        }
        return null;
    }

    /**
     * Access a single value for a row and column by integer position, gets all the lists
     * of the files in the directory before searching
     *
     * @param row The index from the list to access
     * @param column The number of column to access
     * @return The value of the element
     */
    @Override
    public String iat(int row, int column) {
        Map<String, Object> map;
        List<Map<String, Object>> list1 = new LinkedList<>();
        for (DataFrame child: children){
            list1.addAll(child.getList());
        }
        map = list1.get(row);
        String key = map.keySet().toArray()[column].toString();
        if (map.get(key) != null) {
            return map.get(key).toString().trim();
        }
        return null;
    }

    /**
     * @return The number of labels in the list
     */
    @Override
    public int columns() {
        int columns = 0;
        for (DataFrame child: children){
            columns = columns + child.columns();
        }
        return columns/children.size();
    }

    /**
     * @return The number of items all the lists in the directory
     */
    @Override
    public int size() {
        int size = 0;
        for(DataFrame child: children){
            size = size + child.size();
        }
        return size;
    }

    /**
     * Sort a list in ascending or descending order
     * @param label         The label of the column what we want to sort
     * @param comparator    The condition for sort the list
     * @return  The list with the values of the label following a certain order
     */
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
