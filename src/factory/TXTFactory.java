package factory;

public class TXTFactory implements AbstractFactory {

    /**
     * Creates a TXT file reader
     *
     * @return Returns a new TXTReader
     */
    public TXTReader createReader() {
        return new TXTReader();
    }
}
