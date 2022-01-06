package observerDynamicProxy.test;

import observerDynamicProxy.DataframeProxy;
import observerDynamicProxy.ProxyDataframe;
import observerDynamicProxy.TestInvocationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class TestInvocationHandlerJUnitTest {
    DataframeProxy t;

    {
        try {
            t = (DataframeProxy) Proxy.newProxyInstance(DataframeProxy.class.getClassLoader(),
                   new Class<?>[] {DataframeProxy.class},
                   new TestInvocationHandler(new ProxyDataframe(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testProxy()  {
        System.out.println("Testing a creation of a dataframe with dynamic proxy...");
        Assertions.assertNotNull(t);
        System.out.println(t.at(3,"City"));
        System.out.println(t.iat(3,2));
        System.out.println(t.columns());
    }
}
