package visitor;

import api.DataFrameAPI;
import composite.DirectoryDataframe;
import dataframe.DataFrame;


import java.util.List;
import java.util.Map;

public class SumVisitor implements DataframeVisitor {

    double status;

    public double getStatus() {
        return status;
    }


    /**
     * Gets the total sum of values of the label passed found in the dataframe
     *
     * @param d Dataframe with data
     * @param label The field we want to check
     */
    @Override
    public void visit(DataFrameAPI d, String label) {
        List<Map<String, Object>> list;
        list = d.getList();
        double sum = 0;
        for (Map<String, Object> item : list) {
            sum += (Double) item.get(label);
        }
        status = sum;
    }

    /**
     * Gets the total sum of values of the label passed found in the dataframe and in its subdirectories
     *
     * @param d DirectoryDataframe with data and other DataFrames
     * @param label The field we want to check
     */
    @Override
    public void visit(DirectoryDataframe d, String label) {
        List<Map<String, Object>> list;
        double sum = 0;
        for (DataFrame child : d.getChildren()) {
            list = child.getList();
            for (Map<String, Object> item : list) {
                sum += (Double) item.get(label);
            }
        }
        status = sum;
    }
}
