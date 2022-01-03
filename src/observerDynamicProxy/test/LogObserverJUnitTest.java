package observerDynamicProxy.test;

import observerDynamicProxy.LogObserver;
import observerDynamicProxy.ProxyDataframe;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LogObserverJUnitTest {

    ProxyDataframe proxyDataframe,proxyDataframe2,proxyDataframe3;
    LogObserver logObserver = new LogObserver();

    {
        proxyDataframe = new ProxyDataframe(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv");
        proxyDataframe2 = new ProxyDataframe(".\\src\\composite\\EU\\Germany.json","json");
        proxyDataframe3 = new ProxyDataframe(".\\src\\composite\\NA\\Canada.txt","txt");
        proxyDataframe.attach(logObserver);
        proxyDataframe2.attach(logObserver);
        proxyDataframe3.attach(logObserver);

    }

    @Test
    public void testUpdate() throws IOException {
        proxyDataframe.getProxy();
        proxyDataframe2.getProxy();
        proxyDataframe3.getProxy();
        System.out.println("Testing LogObserver...");
        System.out.println("\nExpected LogObserver result: at - 2 - City");
        proxyDataframe.at(2,"City");
        System.out.println("\nExpected LogObserver result: iat - 3 - 4");
        proxyDataframe.iat(3,4);
        System.out.println("\nExpected LogObserver result: columns");
        proxyDataframe2.columns();
        System.out.println("\nExpected LogObserver result: size");
        proxyDataframe2.size();
        System.out.println("\nExpected LogObserver result: sort - City - ascending");
        proxyDataframe3.sort("City", "ascending");
        System.out.println("\nExpected LogObserver result: greater - LatD - 49.0");
        proxyDataframe3.query(proxyDataframe3.greater("LatD",49));
    }
}
