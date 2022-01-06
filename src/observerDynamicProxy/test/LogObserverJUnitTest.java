package observerDynamicProxy.test;

import observerDynamicProxy.DataframeProxy;
import observerDynamicProxy.LogObserver;
import observerDynamicProxy.ProxyDataframe;
import observerDynamicProxy.TestInvocationHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class LogObserverJUnitTest {

    DataframeProxy proxyDataframe,proxyDataframe2,proxyDataframe3;
    LogObserver logObserver = new LogObserver();

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

    }

    @Test
    public void testUpdate() {
        System.out.println("Testing LogObserver...");
        System.out.println("\nExpected a LogObserver log of at operation");
        proxyDataframe.at(2,"City");
        System.out.println("\nExpected a LogObserver log of iat operation");
        proxyDataframe.iat(3,4);
        System.out.println("\nExpected a LogObserver log of columns operation");
        proxyDataframe2.columns();
        System.out.println("\nExpected a LogObserver log of size operation");
        proxyDataframe2.size();
        System.out.println("\nExpected a LogObserver log of sort operation");
        proxyDataframe3.sort("City", "ascending");
        System.out.println("\nExpected a LogObserver log of query greater operation");
        proxyDataframe3.query(proxyDataframe3.greater("LatD",49));
    }
}
