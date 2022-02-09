package visitor;

import dataframe.DataFrame;

public interface DataframeVisitor {
    void visit(DataFrame d);
}
