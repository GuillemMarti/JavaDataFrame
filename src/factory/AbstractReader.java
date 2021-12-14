package factory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AbstractReader {
    /**
     * Method for creating an abstract reader
     *
     * @param filenamePath The path of the file to read
     * @return Returns a list with maps of the information from the file
     * @throws IOException Exception thrown if there is a problem when reading from the file
     */
    List<Map<String, Object>> createReader(String filenamePath) throws IOException;
}
