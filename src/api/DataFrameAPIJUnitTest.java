package api;

import factory.AbstractFactory;
import factory.AbstractReader;
import factory.JSONFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DataFrameAPIJUnitTest {

    AbstractFactory factoryJSON = new JSONFactory();
    AbstractReader jsonReader = factoryJSON.createReader();
    DataFrameAPI df;
    List<Map<String, Object>> list;

    { try {
            df = new DataFrameAPI(jsonReader.createReader(".\\src\\api\\APIFiles\\cities.json"));
            list = new ArrayList<>(jsonReader.createReader(".\\src\\api\\APIFiles\\cities.json"));
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Test
    public void testAt() {
        System.out.println("\n\nTesting At...");
        Assertions.assertEquals(list.get(0).get("LatD").toString().trim(),df.at(0, "LatD"));
    }

    @Test
    public void testIat() {
        System.out.println("\n\nTesting Iat...");
        Assertions.assertEquals(list.get(0).get("LatD").toString().trim(),df.iat(0, 0));
    }

    @Test
    public void testColumns() {
        System.out.println("\n\nTesting Columns...");
        Assertions.assertEquals(list.get(0).size(),df.columns());
    }

    @Test
    public void testSize() {
        System.out.println("\n\nTesting Size...");
        Assertions.assertEquals(list.size(),df.size());
    }

    @Test
    public void testSort() {
        String ascending = "ascending", descending = "descending";
        List<String> list1 = new ArrayList<>();
        for (var map : list) {
            list1.add(map.get("LatD").toString().trim());
        }
        list1.sort(Comparator.reverseOrder());

        System.out.println("\n\nTesting Sort...");
        Assertions.assertEquals(list1,df.sort("LatD", descending));

        list1.sort(Comparator.naturalOrder());
        Assertions.assertEquals(list1,df.sort("LatD", ascending));

    }

    @Test
    public void testQuery() {
        List<Map<String, Object>> list1 = new ArrayList<>();
        List<Map<String, Object>> list2 = new ArrayList<>();
        List<Map<String, Object>> list3 = new ArrayList<>();
        List<Map<String, Object>> list4 = new ArrayList<>();

        for (var map : list) {
            if (map.get("City").equals("Wilmington"))
                list1.add(map);
        }
        for (var map : list) {
            if (map.get("LatD").equals(49.0))
                list2.add(map);
        }
        for (var map : list) {
            if ((Double)map.get("LatD") > 49.0)
                list3.add(map);
        }
        for (var map : list) {
            if ((Double)map.get("LatD") < 45.0)
                list4.add(map);
        }

        System.out.println("\n\nTesting Query...");
        Assertions.assertEquals(list1,df.query(df.equals("City", "Wilmington")));
        Assertions.assertEquals(list2,df.query(df.equals("LatD", 49.0)));
        Assertions.assertEquals(list3,df.query(df.greater("LatD", 49.0)));
        Assertions.assertEquals(list4,df.query(df.lower("LatD", 45.0)));
    }
}
