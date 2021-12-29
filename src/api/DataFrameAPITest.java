package api;

import factory.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class DataFrameAPITest {

    public static void main(String[] args) throws IOException {
        AbstractFactory factoryCSV = new CSVFactory();
        AbstractFactory factoryJSON = new JSONFactory();
        AbstractFactory factoryTXT = new TXTFactory();
        AbstractReader csvReader = factoryCSV.createReader();
        AbstractReader jsonReader = factoryJSON.createReader();
        AbstractReader txtReader = factoryTXT.createReader();
        DataFrameAPI df = new DataFrameAPI(csvReader.createReader(".\\src\\api\\APIFiles\\cities.csv"));
        DataFrameAPI df2 = new DataFrameAPI(jsonReader.createReader(".\\src\\api\\APIFiles\\cities.json"));
        DataFrameAPI df3 = new DataFrameAPI(txtReader.createReader(".\\src\\api\\APIFiles\\cities.txt"));
        String ascending = "ascending", descending = "descending";
        int i = 0;

        System.out.println("Testing API with CSV...");

        System.out.println(df.at(0, "LatD"));
        System.out.println(df.iat(0,0));
        System.out.println(df.columns());
        System.out.println(df.size());
        System.out.println(df.sort("City", descending));
        System.out.println(df.query(df.equals("City", "Wilmington")));
        System.out.println(df.query(df.equals("LatD", 49.0)));
        System.out.println(df.query(df.greater("LatD",45.0)));
        System.out.println(df.query(df.lower("LatD",45.0)));

        for (Map<String, Object> stringObjectMap : df) {
            System.out.println(i + " - " + stringObjectMap);
            i++;
        }

        System.out.println("\n\nTesting API with JSON...");

        System.out.println(df2.at(0, "LatD"));
        System.out.println(df2.iat(0,0));
        System.out.println(df2.columns());
        System.out.println(df2.size());
        System.out.println(df2.sort("LatD", descending));
        System.out.println(df2.query(df2.equals("City", "Wilmington")));
        System.out.println(df2.query(df2.equals("LatD", 49.0)));
        System.out.println(df2.query(df2.greater("LatD",49.0)));
        System.out.println(df2.query(df2.lower("LatD",45.0)));

        Iterator<Map<String, Object>> it2 = df2.iterator();
        i = 0;
        while (it2.hasNext()){
            System.out.println(i + " - " +it2.next());
            i++;
        }

        System.out.println("\n\nTesting API with TXT...");

        System.out.println(df3.at(0, "LatD"));
        System.out.println(df3.iat(0,0));
        System.out.println(df3.columns());
        System.out.println(df3.size());
        System.out.println(df3.sort("LatD", ascending));
        System.out.println(df3.query(df3.equals("City", "Regina")));
        System.out.println(df3.query(df3.equals("LatD",50.0)));
        System.out.println(df3.query(df3.greater("LatD",49.0)));
        System.out.println(df3.query(df3.lower("LatD",45.0)));

        Iterator<Map<String, Object>> it3 = df3.iterator();
        i = 0;
        while (it3.hasNext()){
            System.out.println(i + " - " +it3.next());
            i++;
        }

    }
}
