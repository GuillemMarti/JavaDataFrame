package factory.test;

import factory.AbstractFactory;
import factory.AbstractReader;
import factory.JSONFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class JSONReaderTest {

    @Test
    public void testJsonReader() throws IOException {
        AbstractFactory factory = new JSONFactory();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("LatD", 41.0);
        map.put("LatM", 5.0);
        map.put("LatS", 59.0);
        map.put("NS", "N");
        map.put("LonD", 80.0);
        map.put("LonM", 39.0);
        map.put("LonS", 0.0);
        map.put("EW", "W");
        map.put("City", "Youngstown");
        map.put("State", "OH");
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        System.out.println("Testing JSONReader...");
        AbstractReader jsonReader = factory.createReader();
        Assertions.assertEquals(list,jsonReader.createReader(".\\src\\api\\APIFiles\\citiesTest.json"));
    }

}
