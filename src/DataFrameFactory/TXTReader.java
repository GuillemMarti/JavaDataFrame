package DataFrameFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TXTReader {

    public List<Map<String,Object>> txtToObject(String filenamePath) throws IOException {
        BufferedReader txtReader = new BufferedReader(new FileReader(filenamePath));
        txtReader.readLine();
        String row;
        List<Map<String,Object>> list = new ArrayList<>();
        while ((row = txtReader.readLine()) != null){
            Map<String, Object> map = new LinkedHashMap<>();
            String[] data = row.split(",");
            for(int i = 0; i < data.length; i++){
                map.put(keyColumn(i),data[i]);
            }
            list.add(map);
        }
        return list;
    }

    private String keyColumn (int index){
        switch (index) {
            case 0:
                return "LatD";
            case 1:
                return "LatM";
            case 2:
                return "LatS";
            case 3:
                return "NS";
            case 4:
                return "LonD";
            case 5:
                return "LonM";
            case 6:
                return "LonS";
            case 7:
                return "EW";
            case 8:
                return "City";
            case 9:
                return "State";
            default:
                return null;
        }
    }
}
