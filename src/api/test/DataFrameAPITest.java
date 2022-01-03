package api.test;

import api.DataFrameAPI;

import java.io.IOException;
import java.util.Map;

public class DataFrameAPITest {

    public static void main(String[] args) throws IOException {
        DataFrameAPI df = new DataFrameAPI(".\\src\\api\\APIFiles\\cities.csv","csv");
        DataFrameAPI df2 = new DataFrameAPI(".\\src\\api\\APIFiles\\cities.json","json");
        DataFrameAPI df3 = new DataFrameAPI(".\\src\\api\\APIFiles\\cities.txt","txt");
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

        for (Map<String, Object> map : df) {
            System.out.println(i + " - " + map);
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

        i = 0;
        for (Map<String, Object> map : df2) {
            System.out.println(i + " - " + map);
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

        i = 0;
        for (Map<String, Object> map : df3) {
            System.out.println(i + " - " + map);
            i++;
        }

    }
}
