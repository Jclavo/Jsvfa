package samples.paths;

public class Path7 {

    public void main(String[] var0)
    {
        int a, b;
        
        a = identityX();
        b = a;
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

    public int identityX()
    {
        int a;
        a = identityY();
        return a;
    }

    public int identityY()
    {
        int b;
        b = source();
        return b;
    }

}
