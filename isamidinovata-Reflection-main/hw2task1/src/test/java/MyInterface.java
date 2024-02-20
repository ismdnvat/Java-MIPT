import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Cache { }

public interface MyInterface {
    @Cache
    int methodA();

    int methodB();

    @Cache
    int methodC();

    void methodWithException() throws UnsupportedOperationException;

    @Setter
    void setMethodA(int value);
}
