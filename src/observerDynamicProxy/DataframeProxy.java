package observerDynamicProxy;

import dataframe.DataFrame;

public interface DataframeProxy extends DataFrame {
    void attach(Observer observer);
}
