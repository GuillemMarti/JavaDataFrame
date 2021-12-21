package factory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONReader implements AbstractReader {

    /**
     * Method for reading from JSON files and converting them into an object
     *
     * @param filenamePath The path of the file to read
     * @return Returns a list with maps of the information from the file
     * @throws IOException Exception thrown if there is a problem when reading from the file
     */
    @Override
    public List<Map<String, Object>> createReader(String filenamePath) throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filenamePath));
        List<Map<String, Object>> list = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()){
            Map<String, Object> map = gson.fromJson(reader, Map.class);
            list.add(map);
        }
        reader.close();
        return list;
    }
}
