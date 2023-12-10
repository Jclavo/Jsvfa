package samples.JSVFA.cases.basic;

public class FunctionCall5 {

    public void main(String[] var0)
    {
        int a, b;

        a = 1;

        b = getMagicNumber(a);

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

    public int getMagicNumber(int x) {
        int z;
//        z = source(); CAREFUL: about this case, it is recognized as "sink" when line is uncommented
        z = x * source();
        return z;
    }
}
