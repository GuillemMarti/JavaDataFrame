package api;

import dataframe.DataFrame;
import factory.*;
import visitor.DataframeVisitor;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataFrameAPI implements DataFrame, Iterable<Map<String, Object>> {

    List<Map<String, Object>> list;

    /**
     * Dataframe constructor
     *
     * @param filepath The path to the file we want the data
     * @param fileType The type of file we want to process
     * @throws IOException Signals if an I/O exception has occurred
     */
    public DataFrameAPI(String filepath, String fileType) throws IOException {
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
    public List<String> sort(String label, Comparator<String> comparator) {
        return list.stream().map(f -> f.get(label).toString().trim()).sorted(comparator).collect(Collectors.toList());
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
     * @return Returns an iterator over elements of type Map<String,Object>>
     */
    @Override
    public Iterator<Map<String, Object>> iterator() {
        return this.list.iterator();
    }

    public void accept(DataframeVisitor visitor){
        visitor.visit(this);
    }
}

