package visitor;

import dataframe.DataFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxVisitor implements DataframeVisitor {

    Map<String, Object> status;
    String label;

    /**
     * MaxVisitor constructor
     * @param label The label which the visitor will check
     */
    public MaxVisitor(String label){
        this.label = label;
    }

    public Map<String, Object> getStatus() {
        return status;
    }

    /**
     * Gets the item with the maximum value of the label passed found in the dataframe
     *
     * @param d Dataframe with data
     */
    @Override
    public void visit(DataFrame d) {
        List<Map<String, Object>> list;
        list = d.getList();
        Map<String, Object> maxItem = new HashMap<>();
        double max = 0;
        for (Map<String, Object> item : list) {
            if ((Double) item.get(label) > max) {
                maxItem = item;
                max = (Double) item.get(label);
            }
        }
        status = maxItem;
    }

}
