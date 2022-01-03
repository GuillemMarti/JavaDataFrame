package observerDynamicProxy.test;

import observerDynamicProxy.LogObserver;
import observerDynamicProxy.ProxyDataframe;
import observerDynamicProxy.QueryObserver;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class QueryObserverJUnitTest {

    ProxyDataframe proxyDataframe,proxyDataframe2,proxyDataframe3;
    LogObserver logObserver = new LogObserver();
    QueryObserver queryObserver = new QueryObserver();

    {
        proxyDataframe = new ProxyDataframe(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv");
        proxyDataframe2 = new ProxyDataframe(".\\src\\composite\\EU\\Germany.json","json");
        proxyDataframe3 = new ProxyDataframe(".\\src\\composite\\NA\\Canada.txt","txt");
        proxyDataframe.attach(logObserver);
        proxyDataframe2.attach(logObserver);
        proxyDataframe3.attach(logObserver);
        proxyDataframe.attach(queryObserver);
        proxyDataframe2.attach(queryObserver);
        proxyDataframe3.attach(queryObserver);

    }

    @Test
    public void testUpdate() throws IOException {
        proxyDataframe.getProxy();
        proxyDataframe2.getProxy();
        proxyDataframe3.getProxy();
        System.out.println("Testing QueryObserver...");
        System.out.println("\nExpected LogObserver result: at - 2 - City");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe.at(2,"City");
        System.out.println("\nExpected LogObserver result: iat - 3 - 4");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe.iat(3,4);
        System.out.println("\nExpected LogObserver result: columns");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe2.columns();
        System.out.println("\nExpected LogObserver result: size");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe2.size();
        System.out.println("\nExpected LogObserver result: sort - City - ascending");
        System.out.println("Expected QueryObserver result: none");
        proxyDataframe3.sort("City", "ascending");
        System.out.println("\nExpected LogObserver result: greater - LatD - 49.0");
        System.out.println("Expected QueryObserver result: greater - LatD - 49.0");
        proxyDataframe3.query(proxyDataframe3.greater("LatD",49));
        System.out.println("\nExpected LogObserver result: equals - City - Vancouver");
        System.out.println("Expected QueryObserver result: equals - City - Vancouver");
        proxyDataframe3.query(proxyDataframe3.equals("City","Vancouver"));
        System.out.println("\nExpected LogObserver result: equals - LatD - 49.0");
        System.out.println("Expected QueryObserver result: equals - LatD - 49.0");
        proxyDataframe3.query(proxyDataframe3.equals("LatD",49));
        System.out.println("\nExpected LogObserver result: lower - LatS - 80.0");
        System.out.println("Expected QueryObserver result: lower - LatS - 80.0");
        proxyDataframe3.query(proxyDataframe3.lower("LatS", 80));
    }
}
