package api;

import dataframe.DataFrame;
import visitor.DataframeVisitor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataFrameAPI implements DataFrame, Iterable<Map<String, Object>> {

    List<Map<String, Object>> list;

    /**
     * Dataframe constructor
     *
     * @param list List of maps containing the data information
     */
    public DataFrameAPI(List<Map<String, Object>> list) {
        this.list = list;
    }

    /**
     * @return The list
     */
    public List<Map<String, Object>> getList() {
        return list;
    }

    /**
     * Returns the value of a single item and column label
     *
     * @param row   The item from the list
     * @param label The element we want the value
     * @return The value of the element
     */
    public String at(int row, String label) {
        Map<String, Object> map = list.get(row);
        return (map.get(label).toString().trim());
    }

    /**
     * Access a single value for a row and column by integer position
     *
     * @param row    The index from the list to access
     * @param column The number of column to access
     * @return The value of the element
     */
    public String iat(int row, int column) {
        Map<String, Object> map = list.get(row);
        String key = map.keySet().toArray()[column].toString();
        return (map.get(key).toString().trim());
    }

    /**
     * @return The number of labels in the list
     */
    public int columns() {
        Map<String, Object> map = list.get(0);
        return map.size();
    }

    /**
     * @return The number of items in the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Sort a list in ascending or descending order
     *
     * @param label      The label of the column what we want to sort
     * @param comparator The condition for sort the list
     * @return The list with the values of the label following a certain order
     */
    public List<String> sort(String label, String comparator) {
        List<String> list1 = new ArrayList<>();
        for (var map : list) {
            list1.add(map.get(label).toString().trim());
        }
        switch (comparator.toLowerCase()) {
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
    public List<Map<String, Object>> query(Predicate<Map<String, Object>> predicate) {
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

    /**
     * @return Returns an iterator over elements of type Map<String,Object>>
     */
    @Override
    public Iterator<Map<String, Object>> iterator() {
        return this.list.iterator();
    }

    public void accept(DataframeVisitor visitor, String label){
        visitor.visit(this, label);
    }
}

