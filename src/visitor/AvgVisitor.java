package visitor;

import api.DataFrameAPI;
import composite.DirectoryDataframe;
import dataframe.DataFrame;

import java.util.List;
import java.util.Map;

public class AvgVisitor implements DataframeVisitor {

    double status;
    String label;


    /**
     *  AvgVisitor constructor
     * @param label The label which the visitor will check
     */
    public AvgVisitor(String label){
        this.label = label;
    }

    public double getStatus() {
        return status;
    }

    /**
     * Gets the average value of the label passed found in the dataframe
     *
     * @param d Dataframe with data
     */
    @Override
    public void visit(DataFrameAPI d) {
        List<Map<String, Object>> list;
        list = d.getList();
        double sum = 0;
        double count = 0;
        for (Map<String, Object> item : list) {
            sum += (Double) item.get(label);
            count++;
        }
        status = sum / count;
    }

    /**
     * Gets the average value of the label passed found in the dataframe and in its subdirectories
     *
     * @param d DirectoryDataframe with data and other DataFrames
     */
    @Override
    public void visit(DirectoryDataframe d) {
        List<Map<String, Object>> list;
        double sum = 0;
        double count = 0;
        for (DataFrame child : d.getChildren()) {
            list = child.getList();
            for (Map<String, Object> item : list) {
                sum += (Double) item.get(label);
                count++;
            }
        }
        status = sum / count;
    }
}
