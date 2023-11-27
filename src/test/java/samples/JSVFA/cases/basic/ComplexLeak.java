package samples.JSVFA.cases.basic;

public class ComplexLeak {

    public void main(String[] var0)
    {
        int a, b, result;

        a = source();
        b = source();
        result = a + b;

        sink(result);

        b = 69;

        sink(a);
        sink(b);
    }

    public int source()
    {
        return 0;
    }

    public void sink(int value)
    {
        System.out.println(value);
    }
}
