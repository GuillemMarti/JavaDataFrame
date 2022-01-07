package visitor;

import api.DataFrameAPI;
import composite.DirectoryDataframe;
import dataframe.DataFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinVisitor implements DataframeVisitor {

    Map<String, Object> status;
    String label;

    /**
     * MinVisitor constructor
     * @param label The label which the visitor will check
     */
    public MinVisitor(String label){
        this.label = label;
    }

    public Map<String, Object> getStatus() {
        return status;
    }

    /**
     * Gets the item with the minimum value of the label passed found in the dataframe
     *
     * @param d Dataframe with data
     */
    @Override
    public void visit(DataFrameAPI d) {
        List<Map<String, Object>> list;
        list = d.getList();
        Map<String, Object> minItem = new HashMap<>();
        double min = 200;
        for (Map<String, Object> item : list) {
            if ((Double) item.get(label) < min) {
                minItem = item;
                min = (Double) item.get(label);
            }
        }
        status = minItem;

    }

    /**
     * Gets the item with the minimum value of the label passed found in the dataframe or in its subdirectories
     *
     * @param d DirectoryDataframe with data and other DataFrames
     */
    @Override
    public void visit(DirectoryDataframe d) {
        List<Map<String, Object>> list;
        Map<String, Object> minItem = new HashMap<>();
        double min = 200;
        for (DataFrame child : d.getChildren()) {
            list = child.getList();
            for (Map<String, Object> item : list) {
                if ((Double) item.get(label) < min) {
                    minItem = item;
                    min = (Double) item.get(label);
                }
            }
        }
        status = minItem;
    }
}
