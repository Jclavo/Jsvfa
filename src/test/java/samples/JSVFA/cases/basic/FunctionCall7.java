package samples.JSVFA.cases.basic;

public class FunctionCall7 {

    public void main(String[] var0)
    {
        int a, b, x, y;

        a = source();
        b = source();

        x = identity(a);

        sink(x);

        y = identity(b);

        System.out.println(y);
    }

    public int source()
    {
        return 0;
    }

    public void sink(int value)
    {
        System.out.println(value);
    }

    public int identity(int x) {
        return x;
    }
}
