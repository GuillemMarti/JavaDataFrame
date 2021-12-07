package DataFrameFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    public List<Map<String,Object>> csvToObject(String filenamePath) throws IOException{
        BufferedReader csvReader = new BufferedReader(new FileReader(filenamePath));
        csvReader.readLine();
        String row;
        List<Map<String,Object>> list = new ArrayList<>();
        while ((row = csvReader.readLine()) != null){
            Map<String, Object> map = new LinkedHashMap<>();
            String[] data = row.split(",");
            for(int i=0; i<data.length;i++){
                map.put(keyColumn(i),data[i].replaceAll("\"",""));
            }
            list.add(map);
        }
        return list;
    }

    private String keyColumn (int index){
        return switch (index) {
            case 0 -> "LatD";
            case 1 -> "LatM";
            case 2 -> "LatS";
            case 3 -> "NS";
            case 4 -> "LonD";
            case 5 -> "LonM";
            case 6 -> "LonS";
            case 7 -> "EW";
            case 8 -> "City";
            case 9 -> "State";
            default -> null;
        };
    }

}
