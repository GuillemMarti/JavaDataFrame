package observerDynamicProxy;

public class LogObserver extends Observer{

    /**
     * This method logs in the screen the method that has been called and the parameters passed
     * @param args The method name and parameters passed to the method
     */
    @Override
    public void update(String[] args) {
        String string = "LogObserver - Method and parameters: "+args[0];
        for(int i=1; i<args.length; i++){
            string = string.concat(" - "+args[i]);
        }
        System.out.println(string);
    }
}
