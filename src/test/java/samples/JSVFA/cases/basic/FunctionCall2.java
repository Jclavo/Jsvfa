package samples.JSVFA.cases.basic;

public class FunctionCall2 {

    public void main(String[] var0)
    {
        int a, x;

        a = source();

        x = multiply(a);

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

    public int multiply(int x) {
        int z, result;
        z = 99;
        result = x * z;
        return result;
    }
}
