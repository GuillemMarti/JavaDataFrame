package api;

import factory.*;

import java.io.IOException;

public class mainAPI {

    public static void main(String[] args) throws IOException {
        AbstractFactory factory = new CSVFactory();
        AbstractReader csvReader = factory.createReader();
        DataFrame df = new DataFrame(csvReader.createReader(".\\src\\cities.csv"));
        System.out.println(df.at(0, "LatD"));
        System.out.println(df.iat(0,0));
        System.out.println(df.columns());
        System.out.println(df.size());
    }
}
