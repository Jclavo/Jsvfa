package samples.paths;

public class Path3 {

    public void main(String[] var0)
    {
        int a, b, c, d;

        a = source();
        b = a;
        c = identity(b);
        d = c;
        sink(d);
    }

    public int source()
    {
        return 0;
    }

    public void sink(int value)
    {
        System.out.println(value);
    }

    public int identity(int x)
    {
        return x;
    }

}
