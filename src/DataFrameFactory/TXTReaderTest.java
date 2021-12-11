package DataFrameFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TXTReaderTest {

    @Test
    public void testTXTReader() throws IOException {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("LatD",41);
        map.put("LatM",5);
        map.put("LatS",59);
        map.put("NS","N");
        map.put("LonD",80);
        map.put("LonM",39);
        map.put("LonS",0);
        map.put("EW","W");
        map.put("City","Youngstown");
        map.put("State","OH");
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        System.out.println("Testing TXTReader...");
        TXTReader txtReader = new TXTReader();
        Assertions.assertEquals(list,txtReader.txtToObject(".\\src\\citiesTest.txt"));
    }

}