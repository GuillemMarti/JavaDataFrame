package observerDynamicProxy;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogObserver extends Observer {

    /**
     * This method logs in the screen the method that has been called and the time of execution
     *
     * @param method The method to be logged
     */
    @Override
    public void update(Method method) {
        LocalDateTime now = LocalDateTime.now();
        String string = "LogObserver - Operation logged: " + method.getName() + " | Time of operation: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:hh-mm-ss"));
        System.out.println(string);
    }
}
