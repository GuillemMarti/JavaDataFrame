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
        try{
            proxyDataframe = new ProxyDataframe(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv");
            proxyDataframe2 = new ProxyDataframe(".\\src\\composite\\EU\\Germany.json","json");
            proxyDataframe3 = new ProxyDataframe(".\\src\\composite\\NA\\Canada.txt","txt");
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
        proxyDataframe3.sort("City", "ascending");
        System.out.println("\nExpected a LogObserver log of query greater operation");
        System.out.println("Expected a QueryObserver log of query greater operation");
        proxyDataframe3.query(proxyDataframe3.greater("LatD",49));
        System.out.println("\nExpected a LogObserver log of query equals operation");
        System.out.println("Expected a QueryObserver log of query equals operation");
        proxyDataframe3.query(proxyDataframe3.equals("City","Vancouver"));
        System.out.println("\nExpected a LogObserver log of query equals operation");
        System.out.println("Expected a QueryObserver log of query equals operation");
        proxyDataframe3.query(proxyDataframe3.equals("LatD",49));
        System.out.println("\nExpected a LogObserver log of query lower operation");
        System.out.println("Expected a QueryObserver log of query lower operation");
        proxyDataframe3.query(proxyDataframe3.lower("LatS", 80));
    }
}
