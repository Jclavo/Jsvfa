package samples.paths;

public class Path4 {

    public void main(String[] var0)
    {
        int a, b, c = 0, d;

        a = source();
        b = a;

       while (a > 69) {
           c = b * 1;
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
