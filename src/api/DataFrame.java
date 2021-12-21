package api;

import java.util.*;

public class DataFrame {

    List<Map<String, Object>> list;

    /**
     * DataFrame empty constructor
     */
    public DataFrame() {
        this.list = new ArrayList<>();
    }

    /**
     * Dataframe constructor
     *
     * @param list List of maps containing the data information
     */
    public DataFrame(List<Map<String, Object>> list) {
        this.list = list;
    }

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
     * @param row    The index from the list to acces
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
     * @param label         The label of the column what we want to sort
     * @param comparator    The condition for sort the list
     * @return  The list with the values of the label following a certain order
     */
    public List<String> sort(String label, String comparator) {
        List<String> list1 = new ArrayList<>();
        for (var map : list) {
            list1.add(map.get(label).toString().trim());
        }
        switch (comparator) {
            case "ascending" -> list1.sort(Comparator.naturalOrder());
            case "descending" -> list1.sort(Comparator.reverseOrder());
        }

        return list1;
    }

    /**
     * This function checks for all the items that have the same value (double) in the corresponding
     * label passed by parameter
     *
     * @param key   The label we want to check
     * @param value The value the condition has to fulfill
     * @return Returns a list with the items that contain the same value in the corresponding label
     */
    public List<Map<String, Object>> equals(String key, double value) {
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (var map : list) {
            if (map.get(key).equals(value))
                list1.add(map);
        }
        return list1;
    }

    /**
     * This function checks for all the items that have the same value (string) in the corresponding
     * label passed by parameter
     *
     * @param key   The label we want to check
     * @param value The value the condition has to fulfill
     * @return Returns a list with the items that contain the same value in the corresponding label
     */
    public List<Map<String, Object>> equals(String key, String value) {
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (var map : list) {
            if (map.get(key).equals(value))
                list1.add(map);
        }
        return list1;
    }

    /**
     * This function checks for all the items that have a greater value in the corresponding
     * label passed by parameter
     *
     * @param key   The label we want to check
     * @param value The value the condition has to fulfill
     * @return Returns a list with the items that contain a greater value in the corresponding label
     */
    public List<Map<String, Object>> greater(String key, double value) {
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (var map : list) {
            if ((Double) map.get(key) > value)
                list1.add(map);
        }
        return list1;
    }

    /**
     * This function checks for all the items that have a greater value in the corresponding
     * label passed by parameter
     *
     * @param key   The label we want to check
     * @param value The value the condition has to fulfill
     * @return Returns a list with the items that contain a lower value in the corresponding label
     */
    public List<Map<String, Object>> lower(String key, double value) {
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (var map : list) {
            if ((Double) map.get(key) < value)
                list1.add(map);
        }
        return list1;
    }


}

