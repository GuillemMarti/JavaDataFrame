package observerDynamicProxy;

import factory.*;
import visitor.DataframeVisitor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProxyDataframe implements DataframeProxy {

    List<Map<String, Object>> list;
    List<Observer> observers;

    public ProxyDataframe(String filepath, String fileType) throws IOException {
        observers = new LinkedList<>();
        switch (fileType) {
            case "csv" -> {
                AbstractFactory factoryCSV = new CSVFactory();
                AbstractReader csvReader = factoryCSV.createReader();
                this.list = csvReader.createReader(filepath);
            }
            case "json" -> {
                AbstractFactory factoryJSON = new JSONFactory();
                AbstractReader jsonReader = factoryJSON.createReader();
                this.list = jsonReader.createReader(filepath);
            }
            case "txt" -> {
                AbstractFactory factoryTXT = new TXTFactory();
                AbstractReader txtReader = factoryTXT.createReader();
                this.list = txtReader.createReader(filepath);
            }
        }
    }


    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(Method method){
        for(Observer observer:observers){
            observer.update(method);
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
        try {
            notifyAllObservers(getClass().getMethod("at", int.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
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
        try {
            notifyAllObservers(getClass().getMethod("iat", int.class, int.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return (map.get(key).toString().trim());
    }

    /**
     * @return The number of labels in the list
     */
    @Override
    public int columns()  {
        Map<String, Object> map = list.get(0);
        try {
            notifyAllObservers(getClass().getMethod("columns"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return map.size();
    }

    /**
     * @return The number of items in the list
     */
    @Override
    public int size() {
        try {
            notifyAllObservers(getClass().getMethod("size"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
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
        try {
            notifyAllObservers(getClass().getMethod("sort", String.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
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
    public Predicate<Map<String, Object>> equals(String key, double value)  {
        try {
            notifyAllObservers(getClass().getMethod("equals", String.class, double.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
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
        try {
            notifyAllObservers(getClass().getMethod("equals", String.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
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
        try {
            notifyAllObservers(getClass().getMethod("greater", String.class, double.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
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
        try {
            notifyAllObservers(getClass().getMethod("lower", String.class, double.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return p -> (Double) p.get(key) < (value);
    }

    @Override
    public void accept(DataframeVisitor visitor, String label) {
    }
}
