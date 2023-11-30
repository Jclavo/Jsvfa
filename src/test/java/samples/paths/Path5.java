package samples.paths;

public class Path5 {

    public void main(String[] var0)
    {
        int a, b, c, d, e, f;

        a = source();
        b = a;
        c = identity(b);
        d = c;
        e = identity(d);
        f = e;
        sink(f);
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
