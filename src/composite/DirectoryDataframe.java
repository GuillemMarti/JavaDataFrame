package composite;

import dataframe.DataFrame;
import visitor.DataframeVisitor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DirectoryDataframe implements DataFrame {

    String directoryName;
    List<DataFrame> children;


    public DirectoryDataframe(String directoryName) {
        this.directoryName = directoryName;
        children = new LinkedList<>();
    }

    public void addChild(DataFrame child) {
        children.add(child);
    }

    public void removeChild(DataFrame child) {
        children.remove(child);
    }

    public List<DataFrame> getChildren() {
        return children;
    }

    @Override
    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new LinkedList<>();
        for (DataFrame child : children) {
            list.addAll(child.getList());
        }
        return list;
    }

    /**
     * Returns the value of a single item and column label, gets all the lists
     * of the files in the directory before searching
     *
     * @param row   The item from the list
     * @param label The element we want the value
     * @return The value of the element
     */
    @Override
    public String at(int row, String label) {
        Map<String, Object> map;
        List<Map<String, Object>> list1 = new LinkedList<>();
        for (DataFrame child : children) {
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
     * @param row    The index from the list to access
     * @param column The number of column to access
     * @return The value of the element
     */
    @Override
    public String iat(int row, int column) {
        Map<String, Object> map;
        List<Map<String, Object>> list1 = new LinkedList<>();
        for (DataFrame child : children) {
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
        for (DataFrame child : children) {
            columns = columns + child.columns();
        }
        return columns / children.size();
    }

    /**
     * @return The number of items all the lists in the directory
     */
    @Override
    public int size() {
        int size = 0;
        for (DataFrame child : children) {
            size = size + child.size();
        }
        return size;
    }

    /**
     * Sort a list in ascending or descending order
     *
     * @param label      The label of the column what we want to sort
     * @param comparator The condition for sort the list
     * @return The list with the values of the label following a certain order
     */
    @Override
    public List<String> sort(String label, String comparator) {
        List<String> list1 = new ArrayList<>();
        List<Map<String, Object>> childList;

        for (DataFrame child : children) {
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

    /**
     * This function returns a list according to the condition we request
     *
     * @param predicate The condition we want to search in the DataFrame
     * @return The list with the keys and values according to the requested condition
     */
    @Override
    public List<Map<String, Object>> query(Predicate<Map<String, Object>> predicate) {
        List<Map<String, Object>> list = new LinkedList<>();
        for (DataFrame child : children) {
            list.addAll(child.getList());
        }
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * This function checks the item that have the same value (double) in the corresponding
     * label passed by parameter
     *
     * @param key   The label we want to check
     * @param value The value the condition has to fulfill
     * @return Returns a map if the item contains the same value in the corresponding label
     */
    public Predicate<Map<String, Object>> equals(String key, double value) {
        return p -> p.get(key).equals(value);
    }

    /**
     * This function checks the item that have the same value (string) in the corresponding
     * label passed by parameter
     *
     * @param key   The label we want to check
     * @param value The value the condition has to fulfill
     * @return Returns a map if the item contains the same value in the corresponding label
     */
    public Predicate<Map<String, Object>> equals(String key, String value) {
        return p -> p.get(key).equals(value);
    }

    /**
     * This function checks the item that have a greater value in the corresponding
     * label passed by parameter
     *
     * @param key   The label we want to check
     * @param value The value the condition has to fulfill
     * @return Returns a map if the item contains a greater value in the corresponding label
     */
    public Predicate<Map<String, Object>> greater(String key, double value) {
        return p -> (Double) p.get(key) > (value);
    }

    /**
     * This function checks the item that have a greater value in the corresponding
     * label passed by parameter
     *
     * @param key   The label we want to check
     * @param value The value the condition has to fulfill
     * @return Returns the map if the item contains a lower value in the corresponding label
     */
    public Predicate<Map<String, Object>> lower(String key, double value) {
        return p -> (Double) p.get(key) < (value);
    }

    public void accept(DataframeVisitor visitor, String label){
        visitor.visit(this, label);
    }
}
