public class NonCachedClass implements MyInterface {
    private int valueA;
    private final int valueB;

    public NonCachedClass(int valueA, int valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public int methodA() {
        return valueA;
    }

    public int methodB() {
        return valueB;
    }

    public int methodC() {
        return valueA * valueB;
    }

    public void methodWithException() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMethodA(int value) {
        valueA = value;
    }
}
