package visitor;

import api.DataFrameAPI;
import composite.DirectoryDataframe;

public interface DataframeVisitor {
    void visit(DataFrameAPI d, String label);
    void visit(DirectoryDataframe d, String label);
}
