package api;

import factory.*;

import java.io.IOException;

public class DataFrameTest {

    public static void main(String[] args) throws IOException {
        AbstractFactory factoryCSV = new CSVFactory();
        AbstractFactory factoryJSON = new JSONFactory();
        AbstractFactory factoryTXT = new TXTFactory();
        AbstractReader csvReader = factoryCSV.createReader();
        AbstractReader jsonReader = factoryJSON.createReader();
        AbstractReader txtReader = factoryTXT.createReader();
        DataFrame df = new DataFrame(csvReader.createReader(".\\src\\cities.csv"));
        DataFrame df2 = new DataFrame(jsonReader.createReader(".\\src\\cities.json"));
        DataFrame df3 = new DataFrame(txtReader.createReader(".\\src\\cities.txt"));

        System.out.println("Testing API with CSV...");

        System.out.println(df.at(0, "LatD"));
        System.out.println(df.iat(0,0));
        System.out.println(df.columns());
        System.out.println(df.size());
        System.out.println(df.sort("LatS"));
        System.out.println(df.equals("City", "Wilmington"));
        System.out.println(df.equals("LatD",49.0));
        System.out.println(df.greater("LatD",45.0));
        System.out.println(df.lower("LatD",45.0));

        System.out.println("\n\nTesting API with JSON...");

        System.out.println(df2.at(0, "LatD"));
        System.out.println(df2.iat(0,0));
        System.out.println(df2.columns());
        System.out.println(df2.size());
        System.out.println(df2.sort("LatD"));
        System.out.println(df2.equals("City", "Wilmington"));
        System.out.println(df2.equals("LatD",50.0));
        System.out.println(df2.greater("LatD",49.0));
        System.out.println(df2.lower("LatD",45.0));

        System.out.println("\n\nTesting API with TXT...");

        System.out.println(df3.at(0, "LatD"));
        System.out.println(df3.iat(0,0));
        System.out.println(df3.columns());
        System.out.println(df3.size());
        System.out.println(df3.sort("LatD"));
        System.out.println(df3.equals("City", "Regina"));
        System.out.println(df3.equals("LatD",50.0));
        System.out.println(df3.greater("LatD",49.0));
        System.out.println(df3.lower("LatD",45.0));

    }
}
