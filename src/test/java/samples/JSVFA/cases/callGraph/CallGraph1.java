package samples.JSVFA.cases.callGraph;

public class CallGraph1 {

    public void main(String[] var0)
    {
        int a, b, resulTemp, result;

        a = 1;
        b = 2;

        resulTemp = sum(a, b);

        result = absolute(resulTemp);

        show(result);
    }

    public int sum(int x, int y)
    {
        int result;

        result = absolute(x) + absolute(y);

        return result;
    }

    public int absolute(int a)
    {
        int b;

        b = a * -1;

        return b;
    }

    public void show(int v)
    {
        System.out.println(v);
    }
}
