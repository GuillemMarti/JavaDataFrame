package visitor;

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
    public void visit(DataFrame d) {
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
}
