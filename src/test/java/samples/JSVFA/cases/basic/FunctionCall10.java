package samples.JSVFA.cases.basic;

public class FunctionCall10 {

    public void main(String[] var0)
    {
        int a, b, x, y;

        a = source();
        b = 1;

        x = identity(a);

        x = b;

        sink(x);

        y = identity(b);

        sink(y);
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
