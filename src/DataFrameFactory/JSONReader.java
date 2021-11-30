package DataFrameFactory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JSONReader {

    /**Method for reading from a json and converting to an object*/
    public List<Map<String,?>> JsonToGson (String filenamePath) throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filenamePath));
        List<Map<String, ?>> list = gson.fromJson(reader, Map.class);
        reader.close();
        return list;
    }
}
