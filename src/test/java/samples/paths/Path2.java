package samples.paths;

public class Path2 {

    public void main(String[] var0)
    {
        int a, b, c, d;

        a = source();
        b = a;

        if (a > 0) {
            c = a;
        } else {
            c = b;
        }

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

}
