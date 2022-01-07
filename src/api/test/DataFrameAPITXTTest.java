package api.test;

import api.DataFrameAPI;
import factory.AbstractFactory;
import factory.AbstractReader;
import factory.TXTFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DataFrameAPITXTTest {

    AbstractFactory factoryTXT = new TXTFactory();
    AbstractReader txtReader = factoryTXT.createReader();
    DataFrameAPI df;
    List<Map<String, Object>> list;

    { try {
        df = new DataFrameAPI(".\\src\\api\\APIFiles\\cities.txt","txt");
        list = new ArrayList<>(txtReader.createReader(".\\src\\api\\APIFiles\\cities.txt"));
    } catch (IOException e) { e.printStackTrace(); }
    }

    @Test
    public void testAt() {
        System.out.println("\n\nTesting At...");
        Assertions.assertEquals(list.get(0).get("LatD").toString().trim(),df.at(0, "LatD"));
        System.out.println(list.get(0).get("LatD").toString().trim()+" | "+df.at(0, "LatD"));
    }

    @Test
    public void testIat() {
        System.out.println("\n\nTesting Iat...");
        Assertions.assertEquals(list.get(0).get("LatD").toString().trim(),df.iat(0, 0));
        System.out.println(list.get(0).get("LatD").toString().trim()+" | "+df.iat(0, 0));

    }

    @Test
    public void testColumns() {
        System.out.println("\n\nTesting Columns...");
        Assertions.assertEquals(list.get(0).size(),df.columns());
        System.out.println(list.get(0).size()+" | "+df.columns());

    }

    @Test
    public void testSize() {
        System.out.println("\n\nTesting Size...");
        Assertions.assertEquals(list.size(),df.size());
        System.out.println(list.size()+" | "+df.size());

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
        System.out.println(list1+" | "+df.sort("LatD", descending));


        list1.sort(Comparator.naturalOrder());
        Assertions.assertEquals(list1,df.sort("LatD", ascending));
        System.out.println(list1+" | "+df.sort("LatD", ascending));
    }

    @Test
    public void testQuery() {
        List<Map<String, Object>> list1 = new ArrayList<>();
        List<Map<String, Object>> list2 = new ArrayList<>();
        List<Map<String, Object>> list3 = new ArrayList<>();
        List<Map<String, Object>> list4 = new ArrayList<>();

        for (var map : list) {
            if (map.get("City").equals("Regina"))
                list1.add(map);
        }
        for (var map : list) {
            if (map.get("LatD").equals(48.0))
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
        Assertions.assertEquals(list1,df.query(f->f.get("City").equals("Regina")));
        System.out.println(list1+" | "+df.query(f->f.get("City").equals("Regina")));

        Assertions.assertEquals(list2,df.query(f->f.get("LatD").equals(48.0)));
        System.out.println(list2+" | "+df.query(f->f.get("LatD").equals(48.0)));

        Assertions.assertEquals(list3,df.query(f->(Double)f.get("LatD")>(49.0)));
        System.out.println(list3+" | "+df.query(f->(Double)f.get("LatD")>(49.0)));

        Assertions.assertEquals(list4,df.query(f->(Double)f.get("LatD")<(45.0)));
        System.out.println(list4+" | "+df.query(f->(Double)f.get("LatD")<(45.0)));
    }
}
