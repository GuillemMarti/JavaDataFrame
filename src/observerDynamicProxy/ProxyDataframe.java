package observerDynamicProxy;

import dataframe.DataFrame;
import factory.*;
import visitor.DataframeVisitor;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProxyDataframe implements DataFrame {

    private final String filename;
    private final String fileType;
    List<Map<String, Object>> list;
    List<Observer> observers;

    public ProxyDataframe(String filepath, String fileType) {
        this.filename = filepath;
        this.fileType = fileType;
        observers = new LinkedList<>();
    }

    public void getProxy() throws IOException {
        if (list == null){
            switch (fileType) {
                case "csv" -> {
                    AbstractFactory factoryCSV = new CSVFactory();
                    AbstractReader csvReader = factoryCSV.createReader();
                    this.list = csvReader.createReader(filename);
                }
                case "json" -> {
                    AbstractFactory factoryJSON = new JSONFactory();
                    AbstractReader jsonReader = factoryJSON.createReader();
                    this.list = jsonReader.createReader(filename);
                }
                case "txt" -> {
                    AbstractFactory factoryTXT = new TXTFactory();
                    AbstractReader txtReader = factoryTXT.createReader();
                    this.list = txtReader.createReader(filename);
                }
            }
        }else {
            System.out.println("Proxy Dataframe already loaded");
        }
    }
    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(String[] args){
        for(Observer observer:observers){
            observer.update(args);
        }
    }

    @Override
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
    @Override
    public String at(int row, String label) {
        Map<String, Object> map = list.get(row);
        String[] args = {"at", String.valueOf(row), label};
        notifyAllObservers(args);
        return (map.get(label).toString().trim());
    }

    /**
     * Access a single value for a row and column by integer position
     *
     * @param row    The index from the list to access
     * @param column The number of column to access
     * @return The value of the element
     */
    @Override
    public String iat(int row, int column) {
        Map<String, Object> map = list.get(row);
        String key = map.keySet().toArray()[column].toString();
        String[] args = {"iat", String.valueOf(row), String.valueOf(column)};
        notifyAllObservers(args);
        return (map.get(key).toString().trim());
    }

    /**
     * @return The number of labels in the list
     */
    @Override
    public int columns() {
        Map<String, Object> map = list.get(0);
        String[] args = {"columns"};
        notifyAllObservers(args);
        return map.size();
    }

    /**
     * @return The number of items in the list
     */
    @Override
    public int size() {
        String[] args = {"size"};
        notifyAllObservers(args);
        return list.size();
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
        String[] args = {"sort",label,comparator};
        notifyAllObservers(args);
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
    @Override
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
    @Override
    public Predicate<Map<String, Object>> equals(String key, double value) {
        String[] args = {"equals", key,String.valueOf(value)};
        notifyAllObservers(args);
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
    @Override
    public Predicate<Map<String, Object>> equals(String key, String value) {
        String[] args = {"equals", key, value};
        notifyAllObservers(args);
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
    @Override
    public Predicate<Map<String, Object>> greater(String key, double value) {
        String[] args = {"greater", key, String.valueOf(value)};
        notifyAllObservers(args);
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
    @Override
    public Predicate<Map<String, Object>> lower(String key, double value) {
        String[] args = {"lower", key,String.valueOf(value)};
        notifyAllObservers(args);
        return p -> (Double) p.get(key) < (value);
    }

    @Override
    public void accept(DataframeVisitor visitor, String label) {
    }
}
