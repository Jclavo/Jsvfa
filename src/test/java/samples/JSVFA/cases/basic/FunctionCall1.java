package samples.JSVFA.cases.basic;

public class FunctionCall1 {

    public void main(String[] var0)
    {
        int a, x;

        a = source();

        x = identity(a);

        sink(x);
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
