package observerDynamicProxy;

import java.lang.reflect.Method;

public abstract class Observer {
    public abstract void update(Method method);
}
