package composite;

import factory.*;

public class CompositeTest {

    public static void main(String[] args){
        AbstractFactory factoryCSV = new CSVFactory();
        AbstractFactory factoryJSON = new JSONFactory();
        AbstractFactory factoryTXT = new TXTFactory();
        AbstractReader csvReader = factoryCSV.createReader();
        AbstractReader jsonReader = factoryJSON.createReader();
        AbstractReader txtReader = factoryTXT.createReader();
        DirectoryDataframe directoryDataframeEU = new DirectoryDataframe(".\\src\\composite\\EU");
        DirectoryDataframe directoryDataframeNA = new DirectoryDataframe(".\\src\\composite\\NA");

    }
}
