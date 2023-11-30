package samples.paths;

public class Path6 {

    public void main(String[] var0)
    {
        int a, b, c, d;

        a = source();
        b = a;
        c = identityX(b);
        d = c;
        System.out.println(d);
    }

    public int source()
    {
        return 0;
    }

    public void sink(int value)
    {
        System.out.println(value);
    }

    public int identityX(int x)
    {
        int a, b;

        a = x;
        b = identityY(a);

        return b;
    }

    public int identityY(int y)
    {
        sink(y);
        return y;
    }

}
