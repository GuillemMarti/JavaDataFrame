package visitor;

import api.DataFrameAPI;
import composite.DirectoryDataframe;

public interface DataframeVisitor {
    void visit(DataFrameAPI d);
    void visit(DirectoryDataframe d);
}
