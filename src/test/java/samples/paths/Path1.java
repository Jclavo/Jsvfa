package samples.paths;

public class Path1 {

    public void main(String[] var0)
    {
        int a, b, c;

        a = source();
        b = a;
        c = b;
        sink(c);
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
