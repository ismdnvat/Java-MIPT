import static org.junit.Assert.*;
import org.junit.Test;


public class CacheProxyTest {
    @Test
    public void testNonCachedMethod() {
        MyInterface obj = new NonCachedClass(10, 20);
        MyInterface cachedObj = CacheProxy.cache(obj);

        int result1 = cachedObj.methodA();
        int result2 = cachedObj.methodA();

        assertEquals(result1, result2);
    }

    @Test
    public void testCacheMethodWithoutSetter() {
        MyInterface obj = new MyClass(10, 20);
        MyInterface cachedObj = CacheProxy.cache(obj);

        assertEquals(10, cachedObj.methodA());
        cachedObj.setMethodA(15);
        assertEquals(10, cachedObj.methodA());
        assertEquals(20, cachedObj.methodB());
    }
    
    @Test
    public void testCacheMethodWithSetter() {
        MyInterface obj = new MyClass(10, 20);
        MyInterface cachedObj = CacheProxy.cache(obj);

        assertEquals(10, cachedObj.methodA());
        cachedObj.setMethodA(15);
        assertEquals(15, cachedObj.methodA());
        assertEquals(20, cachedObj.methodB());

        cachedObj.setMethodA(10);
        assertEquals(10, cachedObj.methodA());
    }

    @Test
    public void testCacheInheritedMethod() {
        MyInterface obj = new MyClass(10, 20);
        MyInterface cachedObj = CacheProxy.cache(obj);

        assertEquals(100, cachedObj.methodC());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMethodWithException() {
        MyInterface obj = new MyClass(10, 20);
        MyInterface cachedObj = CacheProxy.cache(obj);
        cachedObj.methodWithException();
    }
}
