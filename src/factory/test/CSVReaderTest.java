package factory.test;

import factory.AbstractFactory;
import factory.AbstractReader;
import factory.CSVFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVReaderTest {

    @Test
    public void testCSVReader() throws IOException {
        AbstractFactory factory = new CSVFactory();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("LatD", 41.0);
        map.put("LatM", 5.0);
        map.put("LatS", 59.0);
        map.put("NS","N");
        map.put("LonD", 80.0);
        map.put("LonM", 39.0);
        map.put("LonS", 0.0);
        map.put("EW", "W");
        map.put("City", "Youngstown");
        map.put("State", "OH");
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        System.out.println("Testing CSVReader...");
        AbstractReader csvReader = factory.createReader();
        Assertions.assertEquals(list,csvReader.createReader(".\\src\\api\\APIFiles\\citiesTest.csv"));
    }

}
