package composite.test;

import api.DataFrameAPI;
import composite.DirectoryDataframe;
import factory.AbstractFactory;
import factory.AbstractReader;
import factory.CSVFactory;
import factory.JSONFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CompositeJUnitTest {

    AbstractFactory factoryCSV = new CSVFactory();
    AbstractFactory factoryJSON = new JSONFactory();
    AbstractReader jsonReader = factoryJSON.createReader();
    AbstractReader csvReader = factoryCSV.createReader();
    DataFrameAPI df1,df2,df3;
    DirectoryDataframe directoryDataframe1,directoryDataframe2;
    List<Map<String, Object>> list,listAux,listAux2;

    { try {
        df1 = new DataFrameAPI(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv");
        df2 = new DataFrameAPI(".\\src\\composite\\EU\\Spain\\Galicia.csv","csv");
        df3 = new DataFrameAPI(".\\src\\composite\\EU\\Germany.json","json");
        directoryDataframe1 = new DirectoryDataframe(".\\src\\composite\\EU");
        directoryDataframe2 = new DirectoryDataframe(".\\src\\composite\\EU\\Spain");
        list = new ArrayList<>(jsonReader.createReader(".\\src\\composite\\EU\\Germany.json"));
        listAux = new ArrayList<>(csvReader.createReader(".\\src\\composite\\EU\\Spain\\Catalonia.csv"));
        listAux2 = new ArrayList<>(csvReader.createReader(".\\src\\composite\\EU\\Spain\\Galicia.csv"));
        list.addAll(listAux);
        list.addAll(listAux2);
        directoryDataframe1.addChild(df3);
        directoryDataframe1.addChild(directoryDataframe2);
        directoryDataframe2.addChild(df1);
        directoryDataframe2.addChild(df2);
    } catch (IOException e) { e.printStackTrace(); }
    }

    @Test
    public void testAt(){
        System.out.println("\nTesting directory At...");
        Assertions.assertEquals(list.get(2).get("City").toString().trim(),directoryDataframe1.at(2,"City"));
        System.out.println(list.get(2).get("City").toString().trim()+" | "+ directoryDataframe1.at(2,"City"));
        System.out.println("\nTesting subdirectory At...");
        Assertions.assertEquals(list.get(8).get("City").toString().trim(),directoryDataframe1.at(8,"City"));
        System.out.println(list.get(8).get("City").toString().trim()+" | "+ directoryDataframe1.at(8,"City"));
    }

    @Test
    public void testIat(){
        System.out.println("\nTesting directory Iat...");
        Assertions.assertEquals(list.get(5).get("NS").toString().trim(),directoryDataframe1.iat(5, 3));
        System.out.println(list.get(5).get("NS").toString().trim()+" | "+ directoryDataframe1.iat(5,3));
        System.out.println("\nTesting subdirectory Iat...");
        Assertions.assertEquals(list.get(11).get("NS").toString().trim(),directoryDataframe1.iat(11,3));
        System.out.println(list.get(11).get("NS").toString().trim()+" | "+ directoryDataframe1.iat(11,3));
    }

    @Test
    public void testColumns(){
        System.out.println("\nTesting directory Columns...");
        Assertions.assertEquals(list.get(0).size(),directoryDataframe1.columns());
        System.out.println(list.get(0).size()+" | "+ directoryDataframe1.columns());
        System.out.println("\nTesting subdirectory Columns...");
        Assertions.assertEquals(list.get(0).size(),directoryDataframe2.columns());
        System.out.println(list.get(0).size()+" | "+ directoryDataframe2.columns());
    }

    @Test
    public void testSize(){
        System.out.println("\nTesting directory Size...");
        Assertions.assertEquals(list.size(),directoryDataframe1.size());
        System.out.println(list.size()+" | "+ directoryDataframe1.size());
        System.out.println("\nTesting subdirectory Size...");
        Assertions.assertEquals(listAux.size()+listAux2.size(),directoryDataframe2.size());
        System.out.println(listAux.size()+listAux2.size()+" | "+ directoryDataframe2.size());
    }

    @Test
    public void testSort(){
        System.out.println("\nTesting directory Sort...");

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for (var map : list) {
            list1.add(map.get("City").toString().trim());
        }
        list1.sort(Comparator.reverseOrder());

        Assertions.assertEquals(list1,directoryDataframe1.sort("City", Comparator.comparing(String::toString).reversed()));
        System.out.println(list1+" | "+ directoryDataframe1.sort("City", Comparator.comparing(String::toString).reversed()));

        list1.sort(Comparator.naturalOrder());
        Assertions.assertEquals(list1,directoryDataframe1.sort("City", Comparator.comparing(String::toString)));
        System.out.println(list1+" | "+ directoryDataframe1.sort("City", Comparator.comparing(String::toString)));

        System.out.println("\nTesting subdirectory Sort...");
        for (var map : listAux) {
            list2.add(map.get("City").toString().trim());
        }
        for (var map : listAux2) {
            list2.add(map.get("City").toString().trim());
        }
        list2.sort(Comparator.naturalOrder());

        Assertions.assertEquals(list2,directoryDataframe2.sort("City", Comparator.comparing(String::toString)));
        System.out.println(list2+" | "+ directoryDataframe2.sort("City", Comparator.comparing(String::toString)));

        list2.sort(Comparator.reverseOrder());
        Assertions.assertEquals(list2,directoryDataframe2.sort("City", Comparator.comparing(String::toString).reversed()));
        System.out.println(list2+" | "+ directoryDataframe2.sort("City", Comparator.comparing(String::toString).reversed()));
    }

    @Test
    public void testQuery(){
        List<Map<String, Object>> list1 = new ArrayList<>();
        List<Map<String, Object>> list2 = new ArrayList<>();
        List<Map<String, Object>> list3 = new ArrayList<>();
        List<Map<String, Object>> list4 = new ArrayList<>();
        List<Map<String, Object>> listAux = new ArrayList<>();

        System.out.println("\nTesting directory Query...");

        for (var map : list) {
            if (map.get("City").equals("Barcelona"))
                list1.add(map);
        }
        for (var map : list) {
            if (map.get("LatD").equals(43.0))
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

        Assertions.assertEquals(list1,directoryDataframe1.query(f->f.get("City").equals("Barcelona")));
        System.out.println(list1+" | "+ directoryDataframe1.query(f->f.get("City").equals("Barcelona")));

        Assertions.assertEquals(list2,directoryDataframe1.query(f->f.get("LatD").equals(43.0)));
        System.out.println(list2+" | "+ directoryDataframe1.query(f->f.get("LatD").equals(43.0)));

        Assertions.assertEquals(list3,directoryDataframe1.query(f->(Double)f.get("LatD")>49.0));
        System.out.println(list3+" | "+ directoryDataframe1.query(f->(Double)f.get("LatD")>49.0));

        Assertions.assertEquals(list4,directoryDataframe1.query(f->(Double)f.get("LatD")<45.0));
        System.out.println(list4+" | "+ directoryDataframe1.query(f->(Double)f.get("LatD")<45.0));


        System.out.println("\nTesting subdirectory Query...");

        listAux.addAll(this.listAux);
        listAux.addAll(listAux2);
        list1.clear();
        list2.clear();
        list3.clear();
        list4.clear();
        for (var map : listAux) {
            if (map.get("City").equals("Barcelona"))
                list1.add(map);
        }
        for (var map : listAux) {
            if (map.get("LatD").equals(43.0))
                list2.add(map);
        }
        for (var map : listAux) {
            if ((Double)map.get("LatD") > 49.0)
                list3.add(map);
        }
        for (var map : listAux) {
            if ((Double)map.get("LatD") < 45.0)
                list4.add(map);
        }

        Assertions.assertEquals(list1,directoryDataframe2.query(f->f.get("City").equals("Barcelona")));
        System.out.println(list1+" | "+ directoryDataframe2.query(f->f.get("City").equals("Barcelona")));

        Assertions.assertEquals(list2,directoryDataframe2.query(f->f.get("LatD").equals(43.0)));
        System.out.println(list2+" | "+ directoryDataframe2.query(f->f.get("LatD").equals(43.0)));

        Assertions.assertEquals(list3,directoryDataframe2.query(f->(Double)f.get("LatD")>49.0));
        System.out.println(list3+" | "+ directoryDataframe2.query(f->(Double)f.get("LatD")>49.0));

        Assertions.assertEquals(list4,directoryDataframe2.query(f->(Double)f.get("LatD")<45.0));
        System.out.println(list4+" | "+ directoryDataframe2.query(f->(Double)f.get("LatD")<45.0));
    }
}
