package DataFrameFactory;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JSONReaderTest {

    @Test
    public void testJsonReader() throws IOException {
        String json = "[{ \"LatD\": 41, \"LatM\": 5, \"LatS\": 59, \"NS\": \"N\", \"LonD\": 80, \"LonM\": 39, \"LonS\": 0, \"EW\": \"W\", \"City\": \"Youngstown\", \"State\": \"OH\" }]";
        Gson gson = new Gson();
        List<Map<String, ?>> list = gson.fromJson(json, (Type) Map.class);
        System.out.println("Testing JSONReader...");
        JSONReader jsonReader = new JSONReader();
        Assertions.assertSame(jsonReader.JsonToGson(".\\src\\citiesTest.json"), list);
    }

}
