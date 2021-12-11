package DataFrameFactory;

public class JSONFactory implements AbstractFactory {

    /**
     * Creates a JSON file reader
     *
     * @return Returns a new JSONReader
     */
    public JSONReader createReader() {
        return new JSONReader();
    }
}
