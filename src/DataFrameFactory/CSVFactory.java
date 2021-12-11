package DataFrameFactory;

public class CSVFactory implements AbstractFactory {

    /**
     * Creates a CSV file reader
     *
     * @return Returns a new CSVReader
     */
    public AbstractReader createReader() {
        return new CSVReader();
    }
}
