package factory;

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
        String[] titles = txtReader.readLine().split(",");
        String row;
        List<Map<String, Object>> list = new ArrayList<>();
        while ((row = txtReader.readLine()) != null) {
            Map<String, Object> map = new LinkedHashMap<>();
            String[] data = row.split(",");
            for (int i = 0; i < data.length; i++) {
                map.put(titles[i], data[i]);
            }
            list.add(map);
        }
        return list;
    }

}
