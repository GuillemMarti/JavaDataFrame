package DataFrameFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TXTReader implements AbstractReader {

    /**
     * Method for reading from TXT files and converting them into an object
     *
     * @param filenamePath The path of the file to read
     * @return Returns a list with maps of the information from the file
     * @throws IOException Exception thrown if there is a problem when reading from the file
     */
    @Override
    public List<Map<String, Object>> createReader(String filenamePath) throws IOException {
        BufferedReader txtReader = new BufferedReader(new FileReader(filenamePath));
        txtReader.readLine();
        String row;
        List<Map<String, Object>> list = new ArrayList<>();
        while ((row = txtReader.readLine()) != null) {
            Map<String, Object> map = new LinkedHashMap<>();
            String[] data = row.split(",");
            for (int i = 0; i < data.length; i++) {
                map.put(keyColumn(i), data[i]);
            }
            list.add(map);
        }
        return list;
    }

    private String keyColumn(int index) {
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
