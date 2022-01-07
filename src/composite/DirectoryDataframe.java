package composite;

import dataframe.DataFrame;
import visitor.DataframeVisitor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DirectoryDataframe implements DataFrame {

    String directoryName;
    List<DataFrame> children;

    /**
     * DirectoryDataframe constructor
     *
     * @param directoryName The path to the directory
     */
    public DirectoryDataframe(String directoryName) {
        this.directoryName = directoryName;
        children = new LinkedList<>();
    }

    /**
     * Adds a Dataframe to the list of dataframes in the directory
     *
     * @param child The file or directory to be added
     */
    public void addChild(DataFrame child) {
        children.add(child);
    }

    /**
     * Removes a Dataframe to the list of dataframes in the directory
     *
     * @param child The file or directory to be removed
     */
    public void removeChild(DataFrame child) {
        children.remove(child);
    }

    /**
     * @return The list of children contained in the dataframe
     */
    public List<DataFrame> getChildren() {
        return children;
    }

    /**
     * @return The list of data contained in the dataframe
     */
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
        List<String> list1;
        List<Map<String, Object>> childList= new ArrayList<>();

        for (DataFrame child : children) {
            childList.addAll(child.getList());
        }
        list1 = childList.stream().map(f->f.get(label).toString().trim()).collect(Collectors.toList());
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

    public void accept(DataframeVisitor visitor) {
        visitor.visit(this);
    }
}
