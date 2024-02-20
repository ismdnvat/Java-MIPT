import java.lang.annotation.*;
import java.lang.reflect.InvocationHandler;


import static java.lang.reflect.Proxy.newProxyInstance;

@Retention(RetentionPolicy.RUNTIME)
@interface Cache { }

@Retention(RetentionPolicy.RUNTIME)
@interface Setter { }

class CacheProxy {
    public static <T> T cache(T obj) {
        Class<?> clazz = obj.getClass();
        Class<?>[] interfaces = clazz.getInterfaces();
        InvocationHandler handler = new CacheHandler(obj);
        return (T) newProxyInstance(clazz.getClassLoader(), interfaces, handler);
    }
}