import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;

class CacheHandler implements InvocationHandler {
    private final Object target;
    private final Map<Method, Object> cache = new HashMap<>();
    private final Map<Method, Object> originalValues = new HashMap<>();

    public CacheHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Setter.class) && isSetterInCurrentMethod(method)) {
            originalValues.clear();
        }

        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(target, args);
        }

        if (!originalValues.containsKey(method)) {
            originalValues.put(method, method.invoke(target, args));
            return originalValues.get(method);
        } else {
            if (originalValues.get(method).equals(method.invoke(target, args)) || originalValues.get(method) == null) {
                return cache.computeIfAbsent(method, k -> {
                    try {
                        return method.invoke(target, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                originalValues.put(method, method.invoke(target, args));
                return originalValues.get(method);
            }
        }
    }

    private boolean isSetterInCurrentMethod(Method method) {
        Method[] declaredMethods = target.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().equals(method.getName()) && declaredMethod.isAnnotationPresent(Setter.class)) {
                return true;
            }
        }
        return false;
    }
}
