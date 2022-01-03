package composite.test;

import api.DataFrameAPI;
import composite.DirectoryDataframe;

import java.io.IOException;

public class CompositeTest {

    public static void main(String[] args) throws IOException {
        DirectoryDataframe directoryDataframeEU = new DirectoryDataframe(".\\src\\composite\\EU");
        DirectoryDataframe directoryDataframeNA = new DirectoryDataframe(".\\src\\composite\\NA");
        DirectoryDataframe directoryDataframeSpain = new DirectoryDataframe(".\\src\\composite\\EU\\Spain");
        DataFrameAPI canadaDataframe = new DataFrameAPI(".\\src\\composite\\NA\\Canada.txt","txt");
        DataFrameAPI cataloniaDataframe = new DataFrameAPI(".\\src\\composite\\EU\\Spain\\Catalonia.csv","csv");
        DataFrameAPI galiciaDataframe = new DataFrameAPI(".\\src\\composite\\EU\\Spain\\Galicia.csv","csv");
        DataFrameAPI germanyDataframe = new DataFrameAPI(".\\src\\composite\\EU\\Germany.json","json");
        String ascending = "ascending", descending = "descending";


        directoryDataframeNA.addChild(canadaDataframe);
        directoryDataframeEU.addChild(directoryDataframeSpain);
        directoryDataframeEU.addChild(germanyDataframe);
        directoryDataframeSpain.addChild(cataloniaDataframe);
        directoryDataframeSpain.addChild(galiciaDataframe);

        System.out.println("\n\nTesting NA directory and file dataframe\n\n");

        System.out.println("Testing Canada dataframe:");
        System.out.println(canadaDataframe.at(1, "LonM"));
        System.out.println(canadaDataframe.iat(1,3));
        System.out.println(canadaDataframe.columns());
        System.out.println(canadaDataframe.size());
        System.out.println(canadaDataframe.sort("LonD",descending));

        System.out.println("\nTesting NA dataframe:");
        System.out.println(directoryDataframeNA.at(1,"LonM"));
        System.out.println(directoryDataframeNA.iat(1,3));
        System.out.println(directoryDataframeNA.columns());
        System.out.println(directoryDataframeNA.size());
        System.out.println(directoryDataframeNA.sort("LonD",descending));

        System.out.println("\n\nTesting EU directories and file dataframes\n\n");

        System.out.println("Testing Catalonia dataframe:");
        System.out.println(cataloniaDataframe.at(1, "LonM"));
        System.out.println(cataloniaDataframe.iat(1,6));
        System.out.println(cataloniaDataframe.columns());
        System.out.println(cataloniaDataframe.size());
        System.out.println(cataloniaDataframe.sort("City",descending));

        System.out.println("\nTesting Galicia dataframe:");
        System.out.println(galiciaDataframe.at(3, "LonS"));
        System.out.println(galiciaDataframe.iat(2,3));
        System.out.println(galiciaDataframe.columns());
        System.out.println(galiciaDataframe.size());
        System.out.println(galiciaDataframe.sort("City",ascending));

        System.out.println("\nTesting Germany dataframe:");
        System.out.println(germanyDataframe.at(2, "LonD"));
        System.out.println(germanyDataframe.iat(1,3));
        System.out.println(germanyDataframe.columns());
        System.out.println(germanyDataframe.size());
        System.out.println(germanyDataframe.sort("City",ascending));

        System.out.println("\nTesting Spain dataframe:");
        System.out.println(directoryDataframeSpain.at(3, "EW"));
        System.out.println(directoryDataframeSpain.iat(1,6));
        System.out.println(directoryDataframeSpain.columns());
        System.out.println(directoryDataframeSpain.size());
        System.out.println(directoryDataframeSpain.sort("City",ascending));

        System.out.println("\nTesting EU dataframe:");
        System.out.println(directoryDataframeEU.at(3, "LonM"));
        System.out.println(directoryDataframeEU.iat(3,9));
        System.out.println(directoryDataframeEU.columns());
        System.out.println(directoryDataframeEU.size());
        System.out.println(directoryDataframeEU.sort("City",ascending));

    }
}
