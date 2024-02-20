public class MyClass implements MyInterface {
    private int valueA;
    private final int valueB;

    public MyClass(int valueA, int valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    @Override
    public int methodA() {
        return valueA;
    }

    @Override
    public int methodB() {
        return valueB;
    }

    @Override
    @Cache
    public int methodC() {
        return valueA * valueB;
    }

    @Override
    @Setter
    public void methodWithException() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMethodA(int value) {
        valueA = value;
    }
}