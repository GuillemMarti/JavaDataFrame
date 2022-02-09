package observerDynamicProxy.test;

import observerDynamicProxy.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Comparator;

public class QueryObserverJUnitTest {

    DataframeProxy proxyDataframe,proxyDataframe2,proxyDataframe3;
    LogObserver logObserver = new LogObserver();
    QueryObserver queryObserver = new QueryObserver();

    {
        try{
            proxyDataframe = (DataframeProxy) Proxy.newProxyInstance(DataframeProxy.class.getClassLoader(),
                    new Class<?>[] {DataframeProxy.class},
                    new TestInvocationHandler(new ProxyDataframe(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv")));
            proxyDataframe2 = (DataframeProxy) Proxy.newProxyInstance(DataframeProxy.class.getClassLoader(),
                    new Class<?>[] {DataframeProxy.class},
                    new TestInvocationHandler(new ProxyDataframe(".\\src\\composite\\EU\\Germany.json","json")));
            proxyDataframe3 = (DataframeProxy) Proxy.newProxyInstance(DataframeProxy.class.getClassLoader(),
                    new Class<?>[] {DataframeProxy.class},
                    new TestInvocationHandler(new ProxyDataframe(".\\src\\composite\\NA\\Canada.txt","txt")));
        }catch (IOException e){
            e.printStackTrace();
        }
        proxyDataframe.attach(logObserver);
        proxyDataframe2.attach(logObserver);
        proxyDataframe3.attach(logObserver);
        proxyDataframe.attach(queryObserver);
        proxyDataframe2.attach(queryObserver);
        proxyDataframe3.attach(queryObserver);

    }

    @Test
    public void testUpdate() {
        System.out.println("Testing QueryObserver...");
        System.out.println("\nExpected a LogObserver log of at operation");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe.at(2,"City");
        System.out.println("\nExpected a LogObserver log of iat operation");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe.iat(3,4);
        System.out.println("\nExpected a LogObserver log of columns operation");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe2.columns();
        System.out.println("\nExpected a LogObserver log of size operation");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe2.size();
        System.out.println("\nExpected a LogObserver log of sort operation");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe3.sort("City", Comparator.comparing(String::toString));
        System.out.println("\nExpected a LogObserver log of query greater operation");
        System.out.println("Expected a QueryObserver log of query greater operation");
        proxyDataframe3.query(f->(Double)f.get("LatD")>49.0);
        System.out.println("\nExpected a LogObserver log of query equals operation");
        System.out.println("Expected a QueryObserver log of query equals operation");
        proxyDataframe3.query(f->f.get("City").equals("Vancouver"));
        System.out.println("\nExpected a LogObserver log of query equals operation");
        System.out.println("Expected a QueryObserver log of query equals operation");
        proxyDataframe3.query(f->f.get("LatD").equals(49.0));
        System.out.println("\nExpected a LogObserver log of query lower operation");
        System.out.println("Expected a QueryObserver log of query lower operation");
        proxyDataframe3.query(f->(Double)f.get("LatS")<80.0);
    }
}
