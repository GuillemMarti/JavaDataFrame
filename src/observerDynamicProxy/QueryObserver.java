package observerDynamicProxy;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QueryObserver extends Observer{

    /**
     * This method only logs in the screen the query methods that have been called and the time of execution
     *
     * @param method The method to be logged
     */
    @Override
    public void update(Method method) {
        LocalDateTime now = LocalDateTime.now();
        if (method.getName().equals("greater") || method.getName().equals("lower") || method.getName().equals("equals")) {
            String string = "QueryObserver - Query logged: " + method.getName() + " | Time of operation: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:hh-mm-ss"));
            System.out.println(string);
        }
    }
}
