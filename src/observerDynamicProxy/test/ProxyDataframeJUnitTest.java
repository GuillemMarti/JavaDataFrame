package observerDynamicProxy.test;

import observerDynamicProxy.ProxyDataframe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ProxyDataframeJUnitTest {

    ProxyDataframe proxyDataframe,proxyDataframe2,proxyDataframe3;

    {
        proxyDataframe = new ProxyDataframe(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv");
        proxyDataframe2 = new ProxyDataframe(".\\src\\composite\\EU\\Germany.json","json");
        proxyDataframe3 = new ProxyDataframe(".\\src\\composite\\NA\\Canada.txt","txt");

    }

    @Test
    public void testProxy() throws IOException {
        System.out.println("Testing getProxy Dataframe...");
        proxyDataframe.getProxy();
        proxyDataframe2.getProxy();
        proxyDataframe3.getProxy();
        System.out.println("Calling again the method should not load again data from files...");
        Assertions.assertNotNull(proxyDataframe.getList());
        proxyDataframe.getProxy();

        Assertions.assertNotNull(proxyDataframe2.getList());
        proxyDataframe2.getProxy();

        Assertions.assertNotNull(proxyDataframe3.getList());
        proxyDataframe3.getProxy();
    }
}
