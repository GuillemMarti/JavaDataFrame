package observerDynamicProxy;

public class QueryObserver extends Observer{

    /**
     * This method only logs in the screen the query mehtods that have been called and  the parameters passed
     * @param args The method name and parameters passed to the method
     */
    @Override
    public void update(String[] args) {
        if (args[0].equals("greater") || args[0].equals("lower") || args[0].equals("equal")) {
            String string = "QueryObserver - Method and parameters: " + args[0];
            for (int i = 1; i < args.length; i++) {
                string = string.concat(" - " + args[i]);
            }
            System.out.println(string);
        }
    }
}
